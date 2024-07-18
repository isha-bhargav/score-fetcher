package com.game.score_listener.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.score_listener.exception.CommonException;
import com.game.score_listener.factory.RepositoryFactory;
import com.game.score_listener.model.User;
import com.game.score_listener.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private RepositoryFactory factory;
    @KafkaListener(topics = "game-score", groupId = "game-score-group")
    public void listen(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(message, User.class);
            scoreService.addScore(user.getUserId(), user.getScore(), user.getType());
            log.info("User {}",user.getUserId());

        } catch (Exception e) {
            log.error("Exception Occurred - {}", e.getMessage());
        }

    }
}
