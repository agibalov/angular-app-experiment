package me.loki2302.be.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DummyApiController {
    @GetMapping("/hello")
    public String getData() {
        return "API says hi!";
    }
}
