package com.medcheck.daoImpl;

import com.medcheck.dao.PatientDao;
import com.medcheck.dao.Database;
import com.medcheck.models.Patient;
import com.medcheck.models.Hospital;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class PatientDaoImpl implements PatientDao {
    Database db;

    public PatientDaoImpl(Database db) {
        this.db = db;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        Hospital hospital = db.getHospitals().stream().filter(h -> h.getId().equals(id)).findFirst().orElse(null);
        if (hospital != null) {
            hospital.getPatients().addAll(patients);
            return "Patients added to hospital successfully";
        }
        return "Hospital not found";
    }

    @Override
    public Patient getPatientById(Long id) {
        for (Hospital hospital : db.getHospitals()) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getId().equals(id)) {
                    return patient;
                }
            }
        }
        return null;
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        Map<Integer, Patient> patientMap = new HashMap<>();
        for (Hospital hospital : db.getHospitals()) {
            for (Patient patient : hospital.getPatients()) {
                patientMap.put(patient.getAge(), patient);
            }
        }
        return patientMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = new ArrayList<>();
        for (Hospital hospital : db.getHospitals()) {
            patients.addAll(hospital.getPatients());
        }
        if ("asc".equalsIgnoreCase(ascOrDesc)) {
            patients.sort((p1, p2) -> Integer.compare(p1.age, p2.age));
        } else if ("desc".equalsIgnoreCase(ascOrDesc)) {
            patients.sort((p1, p2) -> Integer.compare(p2.age, p1.age));
        }
        return patients;
    }
}
