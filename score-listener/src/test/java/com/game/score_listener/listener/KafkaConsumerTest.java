package com.game.score_listener.listener;

import com.game.score_listener.exception.DbException;
import com.game.score_listener.service.ScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaConsumerTest {

    @InjectMocks
    private KafkaConsumer kafkaConsumer;

    @Mock
    private ScoreService service;

    @Test
    public void listenTest()
    {
        String message=" {\"userId\": \"maxScoredUser3\", \"score\": 1,\"type\":\"redis\"}";
        Mockito.doNothing().when(service).addScore("maxScoredUser3",1.0,"redis");
        kafkaConsumer.listen(message);
        Mockito.verify(service,Mockito.times(1)).addScore("maxScoredUser3",1.0,"redis");
    }

    @Test
    public void listenTestException()
    {
        String message=" {\"userId\": \"maxScoredUser3\", \"score\": 1,\"type\":\"redis\"}";
        Mockito.doThrow(new DbException("exception testing")).when(service).addScore("maxScoredUser3",1.0,"redis");
        kafkaConsumer.listen(message);
        Mockito.verify(service,Mockito.times(1)).addScore("maxScoredUser3",1.0,"redis");
    }
}
