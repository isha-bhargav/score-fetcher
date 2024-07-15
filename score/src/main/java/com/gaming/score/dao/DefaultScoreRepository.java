package com.gaming.score.dao;

import com.gaming.score.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("defaultDao")
public class DefaultScoreRepository extends ScoreRepository{
    @Override
    public List<User> fetchTopFiveScores() {
        return null;
    }
}
