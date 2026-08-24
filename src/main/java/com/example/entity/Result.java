package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private String subjectName;

    private Double mse;

    private Double ese;

    private Double finalMarks;

    private String grade;

    public Result() {
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Double getMse() {
        return mse;
    }

    public Double getEse() {
        return ese;
    }

    public Double getFinalMarks() {
        return finalMarks;
    }

    public String getGrade() {
        return grade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setMse(Double mse) {
        this.mse = mse;
    }

    public void setEse(Double ese) {
        this.ese = ese;
    }

    public void setFinalMarks(Double finalMarks) {
        this.finalMarks = finalMarks;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}