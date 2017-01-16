package me.loki2302.be.be.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyApiController {
    @GetMapping("/api/hello")
    public String getData() {
        return "API says hi!";
    }
}
