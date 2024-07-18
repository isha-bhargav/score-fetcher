package com.game.score_listener.factory;

import com.game.score_listener.dao.ScoreRepository;
import com.game.score_listener.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactory {

    @Autowired
    @Qualifier("redisRepository")
    private ScoreRepository redisScoreRepository;

    @Autowired
    @Qualifier("defaultRepository")
    private ScoreRepository defaultScoreRepository;
    public ScoreRepository getScoreRepository(String type)
    {
        if (Constants.REDIS.equals(type))
        {
            return redisScoreRepository;
        }
        else
        {
            return defaultScoreRepository;
        }
    }
}
