package com.gaming.score.service;

import com.gaming.score.dao.ScoreRepository;
import com.gaming.score.factory.RepositoryFactory;
import com.gaming.score.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.gaming.score.utils.Constants.REDIS_USER_SCORE_KEY;

@Service
public class ScoreService {

//    @Autowired
//    private StringRedisTemplate redisTemplate;

@Autowired
private RepositoryFactory repositoryFactory;

    public List<User> getTopScores(String type) {
        ScoreRepository repository = repositoryFactory.getScoreRepository(type);
       return repository.fetchTopFiveScores();
//        Set<ZSetOperations.TypedTuple<String>> topScores = redisTemplate.opsForZSet().reverseRangeWithScores(REDIS_USER_SCORE_KEY, 0, 4);
//        return topScores.stream()
//                .map(tuple -> new User(tuple.getValue(), tuple.getScore().longValue()))
//                .toList();
    }

}
