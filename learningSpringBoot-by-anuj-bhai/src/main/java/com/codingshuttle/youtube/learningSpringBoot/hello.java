package com.codingshuttle.youtube.learningSpringBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    
    @GetMapping("/")
    public String printHello(){
        return "Jay Shree Ram...";
    }
}
