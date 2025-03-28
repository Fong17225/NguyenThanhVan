package com.example.examjava.DTO;

public class StudentGradeDTO {
    private String studentCode;
    private String fullName;
    private String subjectName;
    private String grade;

    public StudentGradeDTO(String studentCode, String fullName, String subjectName, String grade) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.subjectName = subjectName;
        this.grade = grade;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
// Constructor, Getters, and Setters
}
