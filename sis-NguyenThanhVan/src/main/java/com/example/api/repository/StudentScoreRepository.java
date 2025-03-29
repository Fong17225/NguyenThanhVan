package com.example.api.repository;

import com.example.api.entity.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {
}