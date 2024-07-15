package com.game.score_listener.dao;

import org.springframework.stereotype.Repository;

@Repository("defaultRepository")
public class DefaultScoreRepository extends ScoreRepository{
    @Override
    public void saveScore(String userId, double score) {

    }
}
