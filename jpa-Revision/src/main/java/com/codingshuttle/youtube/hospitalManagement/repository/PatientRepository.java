package com.codingshuttle.youtube.hospitalManagement.repository;

import com.codingshuttle.youtube.hospitalManagement.dto.BloodGroupCountResponseDto;
import com.codingshuttle.youtube.hospitalManagement.entity.Patient;
import com.codingshuttle.youtube.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String s);
    
    Patient findByBirthDate(LocalDate date);
    
    List<Patient> findByNameOrEmail(String s, String email);
    
    Patient findByIdAndBirthDate(Long id, LocalDate dob);
    
    List<Patient> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<Patient> findByNameContaining(String name);
    
    List<Patient> findByNameContainingOrderByIdDesc(String name);
    
    @Query("SELECT p FROM Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroupType);
    
    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1 AND p.gender = ?2")
    List<Patient> findByBloodGroupAndGender(@Param("bloodGroup") BloodGroupType bloodGroupType,
                                            @Param("gender") String gender);
    
//    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = :bloodGroup AND p.gender = :gender")
//    List<Patient> findByBloodGroupAndGender(@Param("bloodGroup") BloodGroupType bloodGroupType,
//                                            @Param("gender") String gender);
    
    @Query("SELECT p FROM Patient p WHERE p.birthDate < :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate date);
    
    List<Patient> findByBirthDateBefore(LocalDate date);
    
    @Query("SELECT p.bloodGroup, COUNT(p) FROM Patient p GROUP BY p.bloodGroup")
    List<Object[]> countBloodGroupType();
    
    @Query(value = "SELECT * FROM patient", nativeQuery = true)
//    List<Patient> findAllPatients();
    Page<Patient> findAllPatients(Pageable pageable);
    
    @Query(value = "SELECT blood_group AS Blood_Group, COUNT(*) FROM patient GROUP BY blood_group", nativeQuery = true)
    List<Object[]> countBloodGroupTypeUsingNativeQuery();
    
    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);
    
    @Query("SELECT new com.codingshuttle.youtube.hospitalManagement.dto.BloodGroupCountResponseDto(p.bloodGroup, COUNT(p)) From Patient p GROUP BY p.bloodGroup")
    List<BloodGroupCountResponseDto> countEachBloodGroupType();
    
    
}
