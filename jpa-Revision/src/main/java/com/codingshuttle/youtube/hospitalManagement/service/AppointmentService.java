package com.codingshuttle.youtube.hospitalManagement.service;

import com.codingshuttle.youtube.hospitalManagement.entity.Appointment;
import com.codingshuttle.youtube.hospitalManagement.entity.Doctor;
import com.codingshuttle.youtube.hospitalManagement.entity.Patient;
import com.codingshuttle.youtube.hospitalManagement.repository.AppointmentRepository;
import com.codingshuttle.youtube.hospitalManagement.repository.DoctorRepository;
import com.codingshuttle.youtube.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    
    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id : " + doctorId));
        
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id : " + patientId));
        
        if (appointment.getId() != null) {
            throw new IllegalArgumentException("Appointment should not be in the database.");
        }
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        
        doctor.getAppointments().add(appointment);
        patient.getAppointments().add(appointment);

//        appointmentRepository.save(appointment);
        return appointment;
    }
    
    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id : " + doctorId));
        
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment doesn't exist eith id : " +
                        appointmentId));
        
        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);
        
        return appointment;
    }
    
}
