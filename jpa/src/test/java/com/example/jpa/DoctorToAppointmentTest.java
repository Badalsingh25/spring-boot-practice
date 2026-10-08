package com.example.jpa;

import com.example.jpa.entity.Appointment;
import com.example.jpa.repository.AppointmentRepository;
import com.example.jpa.service.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;

@SpringBootTest
//@Transactional
@RequiredArgsConstructor
public class DoctorToAppointmentTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.now())
                .reason("Cancer")
                .build();
       var newAppointment =  appointmentService.createNewAppointment(appointment,1L,1L);
        System.out.println(newAppointment);

        var updateAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 2L);
        System.out.println(updateAppointment);
    }
    @Test
    public void testAppointmentToPatient(){
        Appointment appointment1 = Appointment.builder()
                .appointmentTime(LocalDateTime.now())
                .reason("Cancer")
                .build();
        Appointment appointment2 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,10,29,10,30))
                .reason("Blood Pressure")
                .build();
        Appointment appointment3 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,2,11,30))
                .reason("Fever")
                .build();

       var appointments =  appointmentService.assignAppointmentToPatient(appointment1,appointment2,appointment3,4L,4L);
       appointments.forEach(System.out::println);

       var newAppointment = appointmentService.disAssociateAppointmentFromPatient(appointment1.getId());
        System.out.println(newAppointment);
    }
}
