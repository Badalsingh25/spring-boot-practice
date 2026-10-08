package com.example.jpa.service;

import com.example.jpa.entity.Appointment;
import com.example.jpa.entity.Doctor;
import com.example.jpa.entity.Patient;
import com.example.jpa.repository.AppointmentRepository;
import com.example.jpa.repository.DoctorRepository;
import com.example.jpa.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment,Long doctorId,Long patientId){

        Doctor doctor = doctorRepository.findById(doctorId)
                        .orElseThrow(()->new EntityNotFoundException("Doctor not found with id " + doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id " + patientId));

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have Id");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment); // Bidirectional maintenance

       return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId,Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();

        Doctor doctor  = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); // this will automatically update because it is dirty.
        doctor.getAppointments().add(appointment); // to maintain bidirectional consistency

        return appointment;
    }

    @Transactional
    public List<Appointment> assignAppointmentToPatient(Appointment appointment1,
                                                        Appointment appointment2,
                                                        Appointment appointment3,
                                                        Long doctorId,
                                                        Long patientId){

       Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
       Patient patient = patientRepository.findById(patientId).orElseThrow();

       appointment1.setDoctor(doctor);
       appointment1.setPatient(patient);

       appointment2.setDoctor(doctor);
       appointment2.setPatient(patient);

       appointment3.setDoctor(doctor);
       appointment3.setPatient(patient);

       patient.getAppointments().add(appointment1);
       patient.getAppointments().add(appointment2);
       patient.getAppointments().add(appointment3);

       List<Appointment> savedAppointments = appointmentRepository.saveAll(List.of(appointment1,appointment2,appointment3));
       return savedAppointments;
    }

    @Transactional
    public Appointment disAssociateAppointmentFromPatient(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();

        appointment.setPatient(null);
        appointment.setDoctor(null);

        return appointmentRepository.save(appointment);
    }
}
