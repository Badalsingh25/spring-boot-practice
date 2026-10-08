package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,orphanRemoval = true)
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();

    @OneToOne(mappedBy = "headDoctor")
    @ToString.Exclude
    private Department department;

    @ManyToMany(mappedBy = "doctors",fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Department> departments = new HashSet<>();
}
