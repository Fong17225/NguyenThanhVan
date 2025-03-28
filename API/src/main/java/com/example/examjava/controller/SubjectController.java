package com.example.examjava.controller;

import com.example.examjava.model.Subject;
import com.example.examjava.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // Insert Subject
    @PostMapping
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    // Get all Subjects
    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    // Get Subject by ID
    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable int id) {
        return subjectService.getSubjectById(id);
    }

    // Update Subject
    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable int id, @RequestBody Subject subject) {
        return subjectService.updateSubject(id, subject);
    }

    // Delete Subject
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable int id) {
        subjectService.deleteSubject(id);
    }
}
