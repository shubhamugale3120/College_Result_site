package com.example.service;

import com.example.entity.Result;
import com.example.entity.Student;
import com.example.repository.ResultRepository;
import com.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    private final StudentRepository studentRepository;
    private final ResultRepository resultRepository;

    public ResultService(StudentRepository studentRepository,
                         ResultRepository resultRepository) {

        this.studentRepository = studentRepository;
        this.resultRepository = resultRepository;
    }

    public Student saveStudent(String name, String prn, Integer semester) {

        Student student = new Student(name, prn, semester);

        return studentRepository.save(student);
    }

    public Result createResult(Student student,
                               String subjectName,
                               double mse,
                               double ese) {

        // MSE = 30 marks
        // ESE = 70 marks

        double finalMarks = mse + ese;

        String grade = calculateGrade(finalMarks);

        Result result = new Result();

        result.setStudent(student);
        result.setSubjectName(subjectName);
        result.setMse(mse);
        result.setEse(ese);
        result.setFinalMarks(finalMarks);
        result.setGrade(grade);

        return resultRepository.save(result);
    }

    private String calculateGrade(double marks) {

        if (marks >= 90) {
            return "O";
        } else if (marks >= 80) {
            return "A+";
        } else if (marks >= 70) {
            return "A";
        } else if (marks >= 60) {
            return "B+";
        } else if (marks >= 50) {
            return "B";
        } else if (marks >= 40) {
            return "C";
        } else {
            return "F";
        }
    }

    public List<Result> getStudentResults(Student student) {

        return resultRepository.findByStudent(student);
    }

    public double calculateTotal(List<Result> results) {

        double total = 0;

        for (Result result : results) {
            total += result.getFinalMarks();
        }

        return total;
    }

    public double calculatePercentage(List<Result> results) {

        if (results.isEmpty()) {
            return 0;
        }

        double total = calculateTotal(results);

        return total / 4;
    }

    public boolean isPassed(List<Result> results) {

        for (Result result : results) {

            if (result.getFinalMarks() < 40) {
                return false;
            }
        }

        return true;
    }
}