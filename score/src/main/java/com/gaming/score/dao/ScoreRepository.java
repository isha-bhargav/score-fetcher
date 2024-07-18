package com.gaming.score.dao;

import com.gaming.score.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class ScoreRepository {
    public abstract List<User> fetchTopFiveScores(Integer topK);
}
