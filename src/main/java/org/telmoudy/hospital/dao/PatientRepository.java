package org.telmoudy.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telmoudy.hospital.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
