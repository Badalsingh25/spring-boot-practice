package com.codingshuttle.youtube.hospitalManagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        
        return new ModelMapper();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    //        @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("admin")
//                .password(passwordEncoder().encode("pass"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2 = User.withUsername("patient")
//                .password(passwordEncoder().encode("pass"))
//                .roles("PATIENT")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

}
