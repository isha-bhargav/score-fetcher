package com.game.score_listener.factory;

import com.game.score_listener.dao.DefaultScoreRepository;
import com.game.score_listener.dao.RedisScoreRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryFactoryTest {
    @InjectMocks
    private RepositoryFactory repositoryFactory;

    @Mock
    private RedisScoreRepository redisScoreRepository;

    @Mock
    private DefaultScoreRepository defaultScoreRepository;

    @Test
    public void getScoreRepositoryTestRedis()
    {

        Assert.assertEquals(redisScoreRepository, repositoryFactory.getScoreRepository("redis"));
    }
    @Test
    public void getScoreRepositoryTestDefault()
    {

        Assert.assertEquals(defaultScoreRepository, repositoryFactory.getScoreRepository("dynamo"));
    }

}
