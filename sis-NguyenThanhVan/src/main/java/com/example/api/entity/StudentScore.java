package com.example.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_score_t")
public class StudentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentScoreId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Double score1;
    private Double score2;

    // Constructor mặc định
    public StudentScore() {
    }

    // Constructor đầy đủ tham số
    public StudentScore(Integer studentScoreId, Student student, Subject subject, Double score1, Double score2) {
        this.studentScoreId = studentScoreId;
        this.student = student;
        this.subject = subject;
        this.score1 = score1;
        this.score2 = score2;
    }

    // Getter và Setter
    public Integer getStudentScoreId() {
        return studentScoreId;
    }

    public void setStudentScoreId(Integer studentScoreId) {
        this.studentScoreId = studentScoreId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Double getScore1() {
        return score1;
    }

    public void setScore1(Double score1) {
        this.score1 = score1;
    }

    public Double getScore2() {
        return score2;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }

    // Phương thức tính Grade
    @Transient
    public String getGrade() {
        double finalScore = 0.3 * score1 + 0.7 * score2;
        if (finalScore >= 8.0) return "A";
        if (finalScore >= 6.0) return "B";
        if (finalScore >= 4.0) return "D";
        return "F";
    }

    // toString
    @Override
    public String toString() {
        return "StudentScore{" +
                "studentScoreId=" + studentScoreId +
                ", student=" + student +
                ", subject=" + subject +
                ", score1=" + score1 +
                ", score2=" + score2 +
                '}';
    }
}