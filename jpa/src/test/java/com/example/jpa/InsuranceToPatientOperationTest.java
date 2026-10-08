package com.example.jpa;

import com.example.jpa.entity.Insurance;
import com.example.jpa.entity.Patient;
import com.example.jpa.repository.InsuranceRepository;
import com.example.jpa.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceToPatientOperationTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Test
    public void testInsurance(){

        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,12,12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);

        var newPatient = insuranceService.disAssociatedInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }
}
