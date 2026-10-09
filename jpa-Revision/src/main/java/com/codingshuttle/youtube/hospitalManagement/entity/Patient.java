package com.codingshuttle.youtube.hospitalManagement.entity;

import com.codingshuttle.youtube.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.print.attribute.IntegerSyntax;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(name = "patient")
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
//    @ToString.Exclude
    private LocalDate birthDate;
    
    private String email;
    
    private String gender;
    
    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
    
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn
    private Insurance insurance; // owning side
    
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<>();
}
