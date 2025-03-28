package com.example.examjava.repository;

import com.example.examjava.model.Score;
import com.example.examjava.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findByStudent(Student student);
    // You can define custom query methods here if needed
}
