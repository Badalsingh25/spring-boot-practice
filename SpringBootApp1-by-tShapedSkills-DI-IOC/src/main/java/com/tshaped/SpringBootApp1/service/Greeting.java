package com.tshaped.SpringBootApp1.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class Greeting {
    
//    @Autowired
//    private LocalTime localTime;
//
//    public Greeting(){
//        System.out.println("Greeting Bean Created... > constructor");
//    }
//
//    public String generateWish(){
//
//        int hour = localTime.getHour();
//
//        if(hour < 12) return "Good Morning !";
//        else if(hour < 16) return "Good AfterNoon !";
//        else if(hour < 20) return "Good Evening !";
//        else return "Good Night !";
//    }
}
