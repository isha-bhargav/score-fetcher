package com.gaming.score.controller;

import com.gaming.score.model.User;
import com.gaming.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("score/")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("top-five")
    public List<User> getTopFiveScores() {
        return scoreService.getTopScores();
    }

}
