package com.example.controller;

import com.example.entity.Result;
import com.example.entity.Student;
import com.example.service.ResultService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/calculate")
    public String calculateResult(

            @RequestParam String name,
            @RequestParam String prn,
            @RequestParam Integer semester,

            @RequestParam double mse1,
            @RequestParam double ese1,

            @RequestParam double mse2,
            @RequestParam double ese2,

            @RequestParam double mse3,
            @RequestParam double ese3,

            @RequestParam double mse4,
            @RequestParam double ese4,

            Model model) {

        // Validate MSE
        if (!validMSE(mse1) ||
            !validMSE(mse2) ||
            !validMSE(mse3) ||
            !validMSE(mse4)) {

            model.addAttribute("error",
                    "MSE marks must be between 0 and 30.");

            return "index";
        }

        // Validate ESE
        if (!validESE(ese1) ||
            !validESE(ese2) ||
            !validESE(ese3) ||
            !validESE(ese4)) {

            model.addAttribute("error",
                    "ESE marks must be between 0 and 70.");

            return "index";
        }

        Student student =
                resultService.saveStudent(name, prn, semester);

        List<Result> results = new ArrayList<>();

        results.add(
                resultService.createResult(
                        student,
                        "Data Structures",
                        mse1,
                        ese1
                )
        );

        results.add(
                resultService.createResult(
                        student,
                        "Database Management System",
                        mse2,
                        ese2
                )
        );

        results.add(
                resultService.createResult(
                        student,
                        "Operating System",
                        mse3,
                        ese3
                )
        );

        results.add(
                resultService.createResult(
                        student,
                        "Computer Networks",
                        mse4,
                        ese4
                )
        );

        double total = resultService.calculateTotal(results);

        double percentage =
                resultService.calculatePercentage(results);

        boolean passed =
                resultService.isPassed(results);

        model.addAttribute("student", student);
        model.addAttribute("results", results);
        model.addAttribute("total", total);
        model.addAttribute("percentage", percentage);
        model.addAttribute("passed", passed);

        return "result";
    }

    private boolean validMSE(double marks) {
        return marks >= 0 && marks <= 30;
    }

    private boolean validESE(double marks) {
        return marks >= 0 && marks <= 70;
    }
}