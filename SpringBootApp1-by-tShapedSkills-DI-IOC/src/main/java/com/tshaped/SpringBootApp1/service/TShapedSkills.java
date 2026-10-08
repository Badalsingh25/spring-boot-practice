package com.tshaped.SpringBootApp1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TShapedSkills {
    
    @Autowired
    @Qualifier("springBoot")
    private ICourse iCourse;
    
    public Boolean buyTheCourse(double amount){
        return iCourse.getTheCourse(amount);
    }
}
