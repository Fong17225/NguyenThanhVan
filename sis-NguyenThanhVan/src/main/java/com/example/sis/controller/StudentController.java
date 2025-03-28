package com.example.sis.controller;

import com.example.sis.entity.Student;
import com.example.sis.entity.StudentScore;
import com.example.sis.entity.Subject;
import com.example.sis.service.StudentService;
import com.example.sis.service.StudentScoreService;
import com.example.sis.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentScoreService studentScoreService;

    @Autowired
    private SubjectService subjectService;

    // Lấy danh sách sinh viên
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Lấy danh sách điểm
    @GetMapping("/student-scores")
    public ResponseEntity<List<StudentScore>> getAllStudentScores() {
        List<StudentScore> scores = studentScoreService.getAllStudentScores();
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    // Lấy sinh viên theo studentCode
    @GetMapping("/students/{studentCode}")
    public ResponseEntity<Student> getStudentByCode(@PathVariable String studentCode) {
        Student student = studentService.findByStudentCode(studentCode);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // Lấy điểm theo studentScoreId
    @GetMapping("/student-scores/{studentScoreId}")
    public ResponseEntity<StudentScore> getStudentScoreById(@PathVariable Integer studentScoreId) {
        StudentScore score = studentScoreService.findById(studentScoreId);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    // Thêm sinh viên và điểm cùng lúc
    @PostMapping("/student-with-score")
    public ResponseEntity<?> createStudentWithScore(@RequestBody StudentScore studentScore) {
        try {
            Student student = studentScore.getStudent();
            Student savedStudent = studentService.createStudent(student);
            Subject subject = subjectService.findBySubjectCode(studentScore.getSubject().getSubjectCode());
            studentScore.setStudent(savedStudent);
            studentScore.setSubject(subject);
            StudentScore savedScore = studentScoreService.createStudentScore(studentScore);
            return new ResponseEntity<>(savedScore, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi thêm sinh viên và điểm: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Sửa sinh viên
    @PutMapping("/students/{studentCode}")
    public ResponseEntity<?> updateStudent(@PathVariable String studentCode, @RequestBody Student student) {
        try {
            Student existingStudent = studentService.findByStudentCode(studentCode);
            existingStudent.setFullName(student.getFullName());
            existingStudent.setAddress(student.getAddress());
            Student updatedStudent = studentService.updateStudent(existingStudent);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi sửa sinh viên: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Xóa sinh viên
    @DeleteMapping("/students/{studentCode}")
    public ResponseEntity<?> deleteStudent(@PathVariable String studentCode) {
        try {
            studentService.deleteStudent(studentCode);
            return new ResponseEntity<>("Xóa sinh viên thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi xóa sinh viên: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Sửa điểm
    @PutMapping("/student-scores/{studentScoreId}")
    public ResponseEntity<?> updateStudentScore(@PathVariable Integer studentScoreId, @RequestBody StudentScore studentScore) {
        try {
            StudentScore existingScore = studentScoreService.findById(studentScoreId);
            existingScore.setScore1(studentScore.getScore1());
            existingScore.setScore2(studentScore.getScore2());
            StudentScore updatedScore = studentScoreService.updateStudentScore(existingScore);
            return new ResponseEntity<>(updatedScore, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi sửa điểm: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Xóa điểm
    @DeleteMapping("/student-scores/{studentScoreId}")
    public ResponseEntity<?> deleteStudentScore(@PathVariable Integer studentScoreId) {
        try {
            studentScoreService.deleteStudentScore(studentScoreId);
            return new ResponseEntity<>("Xóa điểm thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi xóa điểm: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}