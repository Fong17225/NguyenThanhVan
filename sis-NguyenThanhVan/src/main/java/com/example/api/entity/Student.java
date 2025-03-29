package com.example.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_t")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(name = "student_code", nullable = false)
    private String studentCode;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private String address;

    // Constructor mặc định
    public Student() {
    }

    // Constructor đầy đủ tham số
    public Student(Integer studentId, String studentCode, String fullName, String address) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.address = address;
    }

    // Getter và Setter
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentCode='" + studentCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}