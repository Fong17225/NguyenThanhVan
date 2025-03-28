package com.example.examjava.service;

import com.example.examjava.model.Score;
import com.example.examjava.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public Score addScore(Score score) {
        return scoreRepository.save(score);
    }
}
