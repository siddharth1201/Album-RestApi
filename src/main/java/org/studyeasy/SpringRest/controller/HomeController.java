package org.studyeasy.SpringRest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class HomeController {

    @GetMapping("/")
    public String demo(){
        return "Hello world";
    }

    @GetMapping("/test")
    @Tag(name = "test", description = "The Test API")
    public String test(){
        return "test API";
    }
}
