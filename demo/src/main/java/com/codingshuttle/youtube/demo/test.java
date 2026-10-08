package com.codingshuttle.youtube.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping("/")
    public String hello(){
            System.out.println("Hello World!");
        return "Hiiiii";
    }
}
