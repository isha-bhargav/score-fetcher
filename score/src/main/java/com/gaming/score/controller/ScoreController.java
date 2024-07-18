package com.gaming.score.controller;

import com.gaming.score.model.User;
import com.gaming.score.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("score/")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("top")
    public List<User> getTopFiveScores(@RequestParam String type, @RequestParam(defaultValue = "5") Integer topK) {

        return scoreService.getTopScores(type, topK);
    }

}
