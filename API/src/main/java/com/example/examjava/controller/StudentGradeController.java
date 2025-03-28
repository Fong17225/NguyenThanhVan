package com.example.examjava.controller;

import com.example.examjava.DTO.StudentGradeDTO;
import com.example.examjava.model.Student;
import com.example.examjava.model.Score;
import com.example.examjava.repository.StudentRepository;
import com.example.examjava.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentGradeController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    // Display Student Information with Grade
    @GetMapping("/students/grades")
    public List<StudentGradeDTO> getStudentGrades() {
        List<Student> students = studentRepository.findAll();
        List<StudentGradeDTO> studentGradeDTOS = new ArrayList<>();
        for (Student student : students) {
            List<Score> scores = scoreRepository.findByStudent(student);
            for (Score score : scores) {
                StudentGradeDTO studentGradeDTO = new StudentGradeDTO(
                        student.getStudentCode(),
                        student.getFullName(),
                        score.getSubject().getSubjectName(),
                        score.calculateGrade());
                studentGradeDTOS.add(studentGradeDTO);
            }
        }
        return studentGradeDTOS;
    }
}
