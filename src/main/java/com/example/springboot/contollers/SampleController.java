package com.example.springboot.contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SampleController {

    @GetMapping("/title")
    public String getTitle() {
        return "<title>HELLOOOOOOO</title>";
    }
}