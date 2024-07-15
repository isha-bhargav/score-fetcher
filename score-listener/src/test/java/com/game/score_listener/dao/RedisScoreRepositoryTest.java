package com.game.score_listener.dao;

import com.game.score_listener.exception.CommonException;
import com.game.score_listener.exception.DbException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public class RedisScoreRepositoryTest {

    @InjectMocks
    private RedisScoreRepository repository;

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private ZSetOperations<String, String> zSetOperations;



    @Test
    public void saveScore_ShouldSaveScoreSuccessfully() {
        String userId = "user1";
        double score = 10.0;
        Mockito.when(redisTemplate.opsForZSet()).thenReturn(zSetOperations);
        repository.saveScore(userId, score);

        Mockito.verify(zSetOperations, Mockito.times(1)).add(Mockito.anyString(), Mockito.eq(userId), Mockito.eq(score));
    }

    @Test(expected = DbException.class)
    public void saveScore_DbException() {
        String userId = "user1";
        double score = 10.0;
        Mockito.when(redisTemplate.opsForZSet()).thenThrow(new RedisConnectionFailureException("error while connecting to redis"));
        repository.saveScore(userId, score);

        Mockito.verify(zSetOperations, Mockito.times(1)).add(Mockito.anyString(), Mockito.eq(userId), Mockito.eq(score));
    }

    @Test(expected = CommonException.class)
    public void saveScore_CommonException() {
        String userId = "user1";
        double score = 10.0;
        Mockito.when(redisTemplate.opsForZSet()).thenThrow(new NumberFormatException("error while connecting to redis"));
        repository.saveScore(userId, score);

        Mockito.verify(zSetOperations, Mockito.times(0)).add(Mockito.anyString(), Mockito.eq(userId), Mockito.eq(score));
    }

}
