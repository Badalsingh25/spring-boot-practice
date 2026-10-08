package com.example.jpa.entity;

import com.example.jpa.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "patient",
        uniqueConstraints = {
//              @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = {"name","birth_date"})
        },
        indexes = {
                @Index(name="idx_patient_dob",columnList = "birth_date")
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

//    @ToString.Exclude
    private LocalDate birthDate;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    @ToString.Exclude
    @JoinColumn(name = "patient_insurance_id",unique = true) // Owning side
    private Insurance insurance;

    @OneToMany(mappedBy = "patient",cascade = {CascadeType.ALL}, orphanRemoval = true,fetch = FetchType.EAGER) // Inverse Side
//    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();
}
