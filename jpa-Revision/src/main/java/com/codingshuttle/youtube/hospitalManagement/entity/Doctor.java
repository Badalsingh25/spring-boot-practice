package com.codingshuttle.youtube.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(length = 100)
    private String specialization;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();
    
    @OneToOne(mappedBy = "headDoctor")
    private Department department;
    
    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    private List<Department> departments = new ArrayList<>();
    
    
}
