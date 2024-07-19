package com.gaming.auth.service;


import com.gaming.auth.model.User;
import com.gaming.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> bCryptPasswordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}
