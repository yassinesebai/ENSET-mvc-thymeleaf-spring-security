package org.telmoudy.hospital.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telmoudy.hospital.entities.Patient;
import org.telmoudy.hospital.exceptions.ResourceNotFoundException;
import org.telmoudy.hospital.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        return patient;
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.saveOrUpdatePatient(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setScore(patientDetails.getScore());
        patient.setDate_naissance(patientDetails.getDate_naissance());
        return patientService.saveOrUpdatePatient(patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patientService.deletePatient(id);
    }
}
