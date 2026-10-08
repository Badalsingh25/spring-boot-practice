package com.tshaped.SpringBootApp1.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class Java implements ICourse{

    public Java(){
        System.out.println("Java Bean Created");
    }
    
    @Override
    public Boolean getTheCourse(double amount) {
        System.out.println("Java Course is Purchased and fees paid is " + amount);
        return true;
    }
}
