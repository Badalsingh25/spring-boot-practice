package com.codingshuttle.youtube.hospitalManagement;

import com.codingshuttle.youtube.hospitalManagement.entity.Appointment;
import com.codingshuttle.youtube.hospitalManagement.entity.Insurance;
import com.codingshuttle.youtube.hospitalManagement.entity.Patient;
import com.codingshuttle.youtube.hospitalManagement.repository.AppointmentRepository;
import com.codingshuttle.youtube.hospitalManagement.repository.PatientRepository;
import com.codingshuttle.youtube.hospitalManagement.service.AppointmentService;
import com.codingshuttle.youtube.hospitalManagement.service.InsuranceService;
import com.codingshuttle.youtube.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class InsuranceTests {
    
    @Autowired
    private InsuranceService insuranceService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030, 5, 4))
                .build();
        
        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);
        
        var newPatient = insuranceService.disassociatedInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }
    
    @Test
    public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.now())
                .reason("Cancer")
                .build();
        
        var newAppointment =  appointmentService.createNewAppointment(appointment, 2L, 2L);
        System.out.println(newAppointment);
        
        var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 3L);
        System.out.println(updatedAppointment);
    }
    
    @Test
    public void testCreateAppointmentForThreePatient(){
        Appointment appointment1 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2030, 12, 5, 12, 11, 5))
                .reason("fever")
                .build();
        
        Appointment appointment2 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2030, 12, 5, 12, 11, 5))
                .reason("fever")
                .build();
        
        Appointment appointment3 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2030, 12, 5, 12, 11, 5))
                .reason("fever")
                .build();
        
        appointmentService.createNewAppointment(appointment1, 1L, 4L);
        appointmentService.createNewAppointment(appointment2, 1L, 4L);
        appointmentService.createNewAppointment(appointment3, 1L, 4L);
        
        patientService.deletePatient(4L);
        
        assertFalse(patientRepository.existsById(4L));
        
        assertFalse(appointmentRepository.existsById(appointment1.getId()));
        assertFalse(appointmentRepository.existsById(appointment2.getId()));
        assertFalse(appointmentRepository.existsById(appointment3.getId()));
    }
    
}
