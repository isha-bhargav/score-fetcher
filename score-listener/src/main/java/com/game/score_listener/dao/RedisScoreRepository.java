package com.game.score_listener.dao;

import com.game.score_listener.exception.CommonException;
import com.game.score_listener.exception.DbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import static com.game.score_listener.utils.Constants.REDIS_USER_SCORE_KEY;

@Repository("redisRepository")
public class RedisScoreRepository extends ScoreRepository{

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Retryable(value = {DbException.class }, maxAttempts = 3, backoff = @Backoff(2000))
    public void saveScore(String userId, double score) {
        try {
            redisTemplate.opsForZSet().add(REDIS_USER_SCORE_KEY, userId, score);
        }
        catch (DataAccessException e){
            throw new DbException("Error while saving score to redis "+e.getCause());
        }
        catch (Exception e)
        {
            throw new CommonException("Error while saving score to redis");
        }
    }
}
