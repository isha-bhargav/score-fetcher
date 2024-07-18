package com.game.score_listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class ScoreListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoreListenerApplication.class, args);
	}

}
