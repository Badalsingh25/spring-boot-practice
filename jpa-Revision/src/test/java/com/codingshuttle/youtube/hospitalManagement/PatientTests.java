package com.codingshuttle.youtube.hospitalManagement;

import com.codingshuttle.youtube.hospitalManagement.dto.BloodGroupCountResponseDto;
import com.codingshuttle.youtube.hospitalManagement.entity.Patient;
import com.codingshuttle.youtube.hospitalManagement.repository.PatientRepository;
import com.codingshuttle.youtube.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PatientTests {
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private PatientService patientService;
    
    @Test
    public void testPatientRepository() {
        
        List<Patient> patientList = patientRepository.findAllPatientWithAppointment();
        System.out.println(patientList);
    }
    
    @Test
    public void testTransactionMethods() {
//        Patient patient = patientService.getPatientById(1L);
//
//        System.out.println(patient);

//        Patient patient = patientRepository.findByName("aarav Sharma");
//        Patient patient = patientRepository.findByBirthDate(LocalDate.of(1992, 12, 01));

//        List<Patient> patientList = patientRepository.findByNameOrEmail("Aarav Sharma", "neha.iyer@example.com");

//        Patient patient = patientRepository.findByIdAndBirthDate(5L, LocalDate.of(1993, 07, 11));

//        List<Patient> patientList = patientRepository.findByBirthDateBetween(
//                LocalDate.of(1988, 3, 15),
//                LocalDate.of(1995, 8, 20)
//        );

//        List<Patient> patientList = patientRepository.findByNameContainingOrderByIdDesc("a");

//        List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);

//        List<Patient> patientList = patientRepository.findByBloodGroupAndGender(BloodGroupType.A_POSITIVE, "MALE");

//        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(2026, 05, 11));

//        List<Patient> patientList = patientRepository.findByBirthDateBefore(LocalDate.of(1993, 07, 11));

//        List<Object[]> bloodGroupList = patientRepository.countBloodGroupTypeUsingNativeQuery();
//
//        for (Object[] objects : bloodGroupList) {
//            System.out.println(objects[0] + " " + objects[1]);
//        }

//        List<Patient> patientList = patientRepository.findAllPatients();

//        for (Patient patient : patientList) {
//            System.out.println(patient);
//        }

//        int rowsUpdated = patientRepository.updateNameWithId("Karan Sharma", 1L);
//        System.out.println(rowsUpdated);
        
//        List<BloodGroupCountResponseDto> bloodGroupList = patientRepository.countEachBloodGroupType();
//
//        for (BloodGroupCountResponseDto bloodGroupCountResponseDto : bloodGroupList) {
//            System.out.println(bloodGroupCountResponseDto);
//        }
        
//        Page<Patient> patientList = patientRepository.findAllPatients(PageRequest.of(0, 2, Sort.by("birth_date")));
//
//        for (Patient patient : patientList) {
//            System.out.println(patient);
//        }
    
    }
}















