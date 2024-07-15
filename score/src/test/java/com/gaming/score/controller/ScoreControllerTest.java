package com.gaming.score.controller;

import com.gaming.score.model.User;
import com.gaming.score.service.ScoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ScoreControllerTest {
    @InjectMocks
    private ScoreController controller;

    @Mock
    private ScoreService service;

    @Test
    public void getTopFiveScores()
    {
        User user1 = new User("A", 5L);
        User user2 = new User("B", 4L);
        User user3 = new User("C", 3L);
        User user4 = new User("D", 2L);
        User user5 = new User("E", 1L);
        List<User> userList = new ArrayList()
        {
            {
                add(user1);
                add(user2);
                add(user3);
                add(user4);
                add(user5);
            }
        };
        Mockito.when(service.getTopScores("redis")).thenReturn(userList);
        Assert.assertEquals(userList, controller.getTopFiveScores("redis"));
    }
}
