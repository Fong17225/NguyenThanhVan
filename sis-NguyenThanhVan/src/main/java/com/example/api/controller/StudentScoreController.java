package com.example.api.controller;

import com.example.api.entity.StudentScore;
import com.example.api.repository.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/scores")
public class StudentScoreController {
    @Autowired
    private StudentScoreRepository scoreRepository;

    @GetMapping
    public List<StudentScore> getAllScores() {
        return scoreRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createScore(@RequestBody StudentScore score) {
        try {
            StudentScore savedScore = scoreRepository.save(score);
            return new ResponseEntity<>(savedScore, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to create score: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateScore(@PathVariable Integer id, @RequestBody StudentScore score) {
        try {
            Optional<StudentScore> existingScore = scoreRepository.findById(id);
            if (existingScore.isPresent()) {
                StudentScore updatedScore = existingScore.get();
                updatedScore.setStudent(score.getStudent());
                updatedScore.setSubject(score.getSubject());
                updatedScore.setScore1(score.getScore1());
                updatedScore.setScore2(score.getScore2());
                scoreRepository.save(updatedScore);
                return new ResponseEntity<>(updatedScore, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "Score not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to update score: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScore(@PathVariable Integer id) {
        try {
            Optional<StudentScore> score = scoreRepository.findById(id);
            if (score.isPresent()) {
                scoreRepository.deleteById(id);
                return new ResponseEntity<>(Map.of("message", "Score deleted successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "Score not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to delete score: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}