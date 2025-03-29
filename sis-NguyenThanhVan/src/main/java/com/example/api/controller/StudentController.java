package com.example.api.controller;

import com.example.api.entity.Student;
import com.example.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentRepository.save(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to create student: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        try {
            Optional<Student> existingStudent = studentRepository.findById(id);
            if (existingStudent.isPresent()) {
                Student updatedStudent = existingStudent.get();
                updatedStudent.setStudentCode(student.getStudentCode());
                updatedStudent.setFullName(student.getFullName());
                updatedStudent.setAddress(student.getAddress());
                studentRepository.save(updatedStudent);
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "Subject not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to update student: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if (student.isPresent()) {
                studentRepository.deleteById(id);
                return new ResponseEntity<>(Map.of("message", "Student deleted successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "Student not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to delete student: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}