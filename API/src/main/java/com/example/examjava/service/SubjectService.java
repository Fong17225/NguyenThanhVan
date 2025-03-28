package com.example.examjava.service;

import com.example.examjava.model.Subject;
import com.example.examjava.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(int id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public Subject updateSubject(int id, Subject subject) {
        if (subjectRepository.existsById(id)) {
            subject.setSubjectId(id);
            return subjectRepository.save(subject);
        }
        return null;
    }

    public void deleteSubject(int id) {
        subjectRepository.deleteById(id);
    }
}
