package com.familybudget.budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/budget")
public class BudgetServiceApplication {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Budget Service!";
    }

    public static void main(String[] args) {
        SpringApplication.run(BudgetServiceApplication.class, args);
    }
}