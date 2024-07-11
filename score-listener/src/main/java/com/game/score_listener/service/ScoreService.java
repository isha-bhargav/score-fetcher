package com.game.score_listener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.game.score_listener.utils.Constants.REDIS_USER_SCORE_KEY;

@Service
public class ScoreService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void addScore(String userId, double score) {
        redisTemplate.opsForZSet().add(REDIS_USER_SCORE_KEY, userId, score);
    }

}
