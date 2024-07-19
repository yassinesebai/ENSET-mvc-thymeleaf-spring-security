package org.telmoudy.hospital.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telmoudy.hospital.entities.Appointment;
import org.telmoudy.hospital.exceptions.ResourceNotFoundException;
import org.telmoudy.hospital.services.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment == null) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        return appointment;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {

        return appointmentService.saveOrUpdateAppointment(appointment);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment == null) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        appointment.setDoctor(appointmentDetails.getDoctor());
        appointment.setPatient(appointmentDetails.getPatient());
        appointment.setStartTime(appointmentDetails.getStartTime());
        appointment.setEndTime(appointmentDetails.getEndTime());
        return appointmentService.saveOrUpdateAppointment(appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment == null) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        appointmentService.deleteAppointment(id);
    }
}
