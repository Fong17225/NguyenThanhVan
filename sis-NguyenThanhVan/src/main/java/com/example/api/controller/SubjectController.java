package com.example.api.controller;

import com.example.api.entity.Subject;
import com.example.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
        try {
            Subject savedSubject = subjectRepository.save(subject);
            return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to create subject: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        try {
            Optional<Subject> existingSubject = subjectRepository.findById(id);
            if (existingSubject.isPresent()) {
                Subject updatedSubject = existingSubject.get();
                updatedSubject.setSubjectCode(subject.getSubjectCode());
                updatedSubject.setSubjectName(subject.getSubjectName());
                updatedSubject.setCredit(subject.getCredit());
                subjectRepository.save(updatedSubject);
                return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "Subject not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to update subject: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Integer id) {
        try {
            Optional<Subject> subject = subjectRepository.findById(id);
            if (subject.isPresent()) {
                subjectRepository.deleteById(id);
                return new ResponseEntity<>(Map.of("message", "Subject deleted successfully"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "Subject not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to delete subject: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}