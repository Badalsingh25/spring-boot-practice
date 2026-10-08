package com.example.jpa.repository;

import com.example.jpa.dto.BloodGroupCountResponseEntity;
import com.example.jpa.entity.Patient;
import com.example.jpa.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByName(String name);

    Patient findByBirthDate(LocalDate birthDate);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);

    List<Patient> findByBirthDateBetween(LocalDate startDate,LocalDate endDate);

    List<Patient> findByNameContaining(String query);

    List<Patient> findByNameContainingOrderByIdDesc(String query);

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate")LocalDate birthDate);

//    @Query("select p.bloodGroup,count(p) from Patient p group by p.bloodGroup")
//    List<Object[]> countEachBloodGroupType();

//    @Query(value = "Select * from patient",nativeQuery = true)
//   @Query(value = "SELECT id,name,email,birth_date,blood_group,gender,created_at FROM patient", nativeQuery = true)
//    List<Patient> findAllPatients();

    @Transactional
    @Modifying
    @Query("Update Patient p set p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name")String name,@Param("id")Long id);

//    @Query("SELECT DISTINCT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT DISTINCT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientWithAppointment();


    @Query("select new com.example.jpa.dto.BloodGroupCountResponseEntity(p.bloodGroup,count(p)) from Patient p group by p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();


    @Query(value = "Select * from patient",nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);

}
