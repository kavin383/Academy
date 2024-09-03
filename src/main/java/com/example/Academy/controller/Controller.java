package com.example.Academy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class Controller {

    @GetMapping("/book")
    public String addStudent() {
        return "My first output";
    }
}
