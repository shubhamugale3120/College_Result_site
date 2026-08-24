package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String prn;

    @Column(nullable = false)
    private Integer semester;

    public Student() {
    }

    public Student(String name, String prn, Integer semester) {
        this.name = name;
        this.prn = prn;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrn() {
        return prn;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}