package com.gaming.score.dao;

import com.gaming.score.exception.CommonException;
import com.gaming.score.exception.DbException;
import com.gaming.score.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.gaming.score.utils.Constants.REDIS_USER_SCORE_KEY;

@Repository("redisDao")
public class RedisScoreRepository extends ScoreRepository{
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
//    @Retryable(retryFor = {DbException.class }, maxAttempts = 3, backoff = @Backoff(2000))
    public List<User> fetchTopFiveScores(Integer topK) {
        try {
            Set<ZSetOperations.TypedTuple<String>> topScores = redisTemplate.opsForZSet().reverseRangeWithScores(REDIS_USER_SCORE_KEY, 0, topK-1);
            return topScores.stream()
                    .map(tuple -> new User(tuple.getValue(), tuple.getScore().longValue()))
                    .toList();
        }catch (DataAccessException dataAccessException)
        {
            throw new DbException("Error while fetching score to redis" + dataAccessException.getCause());

        }
        catch (Exception e)
        {
            throw new CommonException("Error while fetching score to redis"+e.getCause());
        }
    }
}
