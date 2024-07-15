package com.gaming.score.dao;

import com.gaming.score.model.User;

import java.util.List;

public abstract class ScoreRepository {
    public abstract List<User> fetchTopFiveScores();
}
