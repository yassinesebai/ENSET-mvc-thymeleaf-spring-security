package org.telmoudy.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telmoudy.hospital.entities.Doctor;
import org.telmoudy.hospital.entities.Patient;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
