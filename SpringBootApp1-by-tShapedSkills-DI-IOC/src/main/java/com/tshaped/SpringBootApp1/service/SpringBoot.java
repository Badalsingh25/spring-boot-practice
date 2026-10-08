package com.tshaped.SpringBootApp1.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SpringBoot implements ICourse{
    
    public SpringBoot(){
        System.out.println("SpringBoot Bean Created.");
    }
    
    @Override
    public Boolean getTheCourse(double amount) {
        System.out.println("SpringBoot Course is Purchased and fees paid is " + amount);
        return true;
    }
}
