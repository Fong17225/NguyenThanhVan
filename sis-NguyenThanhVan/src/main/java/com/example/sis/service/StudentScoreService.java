package com.example.sis.service;

import com.example.sis.entity.StudentScore;
import com.example.sis.repository.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentScoreService {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    public List<StudentScore> getAllStudentScores() {
        return studentScoreRepository.findAll();
    }

    public StudentScore createStudentScore(StudentScore studentScore) {
        return studentScoreRepository.save(studentScore);
    }

    public StudentScore findById(Integer studentScoreId) {
        return studentScoreRepository.findById(studentScoreId)
                .orElseThrow(() -> new RuntimeException("StudentScore not found: " + studentScoreId));
    }

    public StudentScore updateStudentScore(StudentScore studentScore) {
        return studentScoreRepository.save(studentScore);
    }

    public void deleteStudentScore(Integer studentScoreId) {
        StudentScore score = findById(studentScoreId);
        studentScoreRepository.delete(score);
    }
}