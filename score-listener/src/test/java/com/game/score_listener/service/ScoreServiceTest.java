package com.game.score_listener.service;

import com.game.score_listener.dao.DefaultScoreRepository;
import com.game.score_listener.dao.RedisScoreRepository;
import com.game.score_listener.factory.RepositoryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ScoreServiceTest {
    @InjectMocks
    private ScoreService scoreService;

    @Mock
    private RepositoryFactory factory;

    @Test
    public void addScoreTestRedis()
    {
        RedisScoreRepository repository= Mockito.mock(RedisScoreRepository.class);

        Mockito.when(factory.getScoreRepository("redis")).thenReturn(repository);
        Mockito.doNothing().when(repository).saveScore("A", 1.0);
        scoreService.addScore("A",1.0,"redis");
        Mockito.verify(repository,Mockito.times(1)).saveScore("A",1.0);

    }

    @Test
    public void addScoreTestDefault()
    {
        DefaultScoreRepository repository= Mockito.mock(DefaultScoreRepository.class);

        Mockito.when(factory.getScoreRepository("dynamo")).thenReturn(repository);
        Mockito.doNothing().when(repository).saveScore("A", 1.0);
        scoreService.addScore("A",1.0,"dynamo");
        Mockito.verify(repository,Mockito.times(1)).saveScore("A",1.0);

    }
}
