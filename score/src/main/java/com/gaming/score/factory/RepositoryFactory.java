package com.gaming.score.factory;

import com.gaming.score.dao.ScoreRepository;
import com.gaming.score.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class RepositoryFactory {
    @Autowired
    @Qualifier("redisDao")
    private ScoreRepository redisScoreRepository;

    @Autowired
    @Qualifier("defaultDao")
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


