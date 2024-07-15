package com.game.score_listener.dao;

public abstract class ScoreRepository {
    public abstract void saveScore(String userId, double score);
}
