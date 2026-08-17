package io.github.ishank2111.enterprise_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping()
    public String hello() {
          return "Hello Devops!";
    }
}