package com.example.examjava.controller;

import com.example.examjava.model.Score;
import com.example.examjava.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    // Insert Score
    @PostMapping
    public Score addScore(@RequestBody Score score) {
        return scoreService.addScore(score);
    }
}
