package com.game.score_listener.service;

import com.game.score_listener.dao.ScoreRepository;
import com.game.score_listener.factory.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.game.score_listener.utils.Constants.REDIS_USER_SCORE_KEY;

@Service
public class ScoreService {

//    @Autowired
//    private StringRedisTemplate redisTemplate;


    @Autowired
    private RepositoryFactory scoreRepoFactory;
    public void addScore(String userId, double score, String type) {

//        redisTemplate.opsForZSet().add(REDIS_USER_SCORE_KEY, userId, score);
//        scoreRepository.addScore(userId, score);
        ScoreRepository scoreRepository = scoreRepoFactory.getScoreRepository(type);
        scoreRepository.saveScore(userId, score);


    }

}
