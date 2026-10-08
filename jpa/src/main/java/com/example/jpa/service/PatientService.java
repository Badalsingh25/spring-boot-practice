package com.example.jpa.service;

import com.example.jpa.entity.Patient;
import com.example.jpa.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    public final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(long id){

        Patient p1 = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient is not found with this id:" + id));
        Patient p2 = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient is not found with this id:" + id));
        System.out.println(p1 == p2);

        return p1;
    }

}
