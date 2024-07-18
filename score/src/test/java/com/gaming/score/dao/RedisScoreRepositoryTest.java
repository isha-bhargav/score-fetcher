package com.gaming.score.dao;

import com.gaming.score.exception.CommonException;
import com.gaming.score.exception.DbException;
import com.gaming.score.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
public class RedisScoreRepositoryTest {

    @InjectMocks
    private RedisScoreRepository repository;
    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private ZSetOperations<String, String> zSetOperations;


    @Test
    public void fetchTopFiveScores_ShouldReturnTopScoresSuccessfully() {
        Set<ZSetOperations.TypedTuple<String>> topScores = new HashSet<>();
        Mockito.when(redisTemplate.opsForZSet()).thenReturn(zSetOperations);

        topScores.add(new ZSetOperations.TypedTuple<String>() {
            @Override
            public String getValue() {
                return "user1";
            }

            @Override
            public Double getScore() {
                return 100.0;
            }

            @Override
            public int compareTo(ZSetOperations.TypedTuple<String> o) {
                return Double.compare(this.getScore(), o.getScore());
            }
        });
        Mockito.when(zSetOperations.reverseRangeWithScores(Mockito.anyString(), Mockito.eq(0L), Mockito.eq(4L))).thenReturn(topScores);

        List<User> users = repository.fetchTopFiveScores(5);

        assert(users.size() == 1);
        assert(users.get(0).getUserId().equals("user1"));
        assert(users.get(0).getScore() == 100);
    }

    @Test(expected = DbException.class)
    public void fetchTopFiveScores_ShouldThrowDbException_OnDataAccessException() {
        Mockito.when(redisTemplate.opsForZSet()).thenReturn(zSetOperations);

        Mockito.when(zSetOperations.reverseRangeWithScores(Mockito.anyString(), Mockito.eq(0L), Mockito.eq(4L)))
                .thenThrow(new DataAccessException("Data access error") {});

            repository.fetchTopFiveScores(5);
    }

    @Test(expected = CommonException.class)
    public void fetchTopFiveScores_ShouldThrowCommonException_OnGenericException() {
        Mockito.when(redisTemplate.opsForZSet()).thenReturn(zSetOperations);

        Mockito.when(zSetOperations.reverseRangeWithScores(Mockito.anyString(), Mockito.eq(0L), Mockito.eq(4L)))
                .thenThrow(new RuntimeException("Unexpected error"));


            repository.fetchTopFiveScores(5);

    }
}
