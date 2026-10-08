package com.example.jpa;

import com.example.jpa.dto.BloodGroupCountResponseEntity;
import com.example.jpa.entity.Patient;
import com.example.jpa.repository.PatientRepository;
import com.example.jpa.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;


    @Test
    public void testPatientRepository(){
//        List<Patient> patientList = patientRepository.findAll();
        List<Patient> patientList = patientRepository.findAllPatientWithAppointment();
        System.out.println(patientList);
    }

    @Test
    public void testTransactionMethods(){
//        Patient patient = patientService.getPatientById(1L);

//        Patient patient = patientRepository.findByName("Rohan Sharma");

//        Patient patient = patientRepository.findByBirthDate(LocalDate.of(1987,9,25));
//        System.out.println(patient);

//        List<Patient> patientList = patientRepository.findByBirthDateOrEmail(
//                LocalDate.of(1987,9,25), "sneha.iyer@gmail.com");
//        for(Patient patient:patientList){System.out.println(patient);}

//        List<Patient> patientList = patientRepository.findByBirthDateBetween(
//                LocalDate.of(1998,5,21),
//                LocalDate.of(2001,8,14));
//                for(Patient patient:patientList){System.out.println(patient);}

//                List<Patient> patientList = patientRepository.findByNameContainingOrderByIdDesc("Nair");
//                for(Patient patient:patientList){System.out.println(patient);}

//                List<Patient> patientList = patientRepository.findByNameContainingOrderByIdDesc("Nair");
//                for(Patient patient:patientList){System.out.println(patient);}

//                List<Patient> patientList1 = patientRepository.findByBloodGroup(BloodGroupType.A_NEGATIVE);
//                for(Patient patient:patientList1){System.out.println(patient);}

//        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(1998,5,21));
//                for(Patient patient:patientList){System.out.println(patient);}

//                List<Object[]> patientBloodGroupList = patientRepository.countEachBloodGroupType();
//                for(Object[] objects:patientBloodGroupList){
//                    System.out.println(objects[0] + " " + objects[1]);
//                }

        Page<Patient> patients = patientRepository.findAllPatients(PageRequest.of(1,5, Sort.by("name")));
        for(Patient patient:patients){
            System.out.println(patient);
        }

//        int patient=patientRepository.updateNameWithId("Rohit Sharma",1L);
//        System.out.println(patient);

//        List<BloodGroupCountResponseEntity> patientBloodGroupList = patientRepository.countEachBloodGroupType();
//                for(BloodGroupCountResponseEntity bloodGroup:patientBloodGroupList){
//                    System.out.println(bloodGroup);
//                }
    }
}