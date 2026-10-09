package com.codingshuttle.youtube.hospitalManagement.service;


import com.codingshuttle.youtube.hospitalManagement.entity.Insurance;
import com.codingshuttle.youtube.hospitalManagement.entity.Patient;
import com.codingshuttle.youtube.hospitalManagement.repository.InsuranceRepository;
import com.codingshuttle.youtube.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    
    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with this id" + patientId));
        
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        
        return patient;
    }
    
    @Transactional
    public Patient disassociatedInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        
        patient.setInsurance(null);
        
        return patient;
    }
    
}
