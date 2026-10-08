package com.tshaped.SpringBootApp1;

import com.tshaped.SpringBootApp1.service.Greeting;
import com.tshaped.SpringBootApp1.service.TShapedSkills;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class SpringBootApp1Application {
    
    public static void main(String[] args) {
        ApplicationContext con = SpringApplication.run(SpringBootApp1Application.class, args);
        
//        Greeting greeting= con.getBean(Greeting.class);
//        System.out.println(greeting.generateWish());
    
        TShapedSkills tShapedSkills = con.getBean(TShapedSkills.class);
        Boolean status = tShapedSkills.buyTheCourse(520.22);
        if (status) {
            System.out.println("Course is purchased successfully with price : ");
        }else {
            System.out.println("Failed to purchase the course : ");
        }
    }
}