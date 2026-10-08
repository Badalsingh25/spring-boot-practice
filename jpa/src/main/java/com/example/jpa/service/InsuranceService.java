package com.example.jpa.service;

import com.example.jpa.entity.Insurance;
import com.example.jpa.entity.Patient;
import com.example.jpa.repository.InsuranceRepository;
import com.example.jpa.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance,Long patientId){
        Patient patient = patientRepository.findById(patientId).
                orElseThrow(()->new EntityNotFoundException("Patient not found with id :" + patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient); // bidirectional consistency maintenance
        return patient;
    }
    @Transactional
    public Patient disAssociatedInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(null);
        return patient;
    }
}
