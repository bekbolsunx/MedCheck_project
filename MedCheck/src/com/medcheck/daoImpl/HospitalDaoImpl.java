package com.medcheck.daoImpl;

import com.medcheck.dao.HospitalDao;
import com.medcheck.models.Hospital;
import com.medcheck.models.Patient;
import com.medcheck.dao.Database;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HospitalDaoImpl implements HospitalDao {
    private Database db;

    public HospitalDaoImpl(Database db) {
        this.db = db;
    }

    @Override
    public String addHospital(Hospital hospital) {
        db.getHospitals().add(hospital);
        return "Hospital added successfully";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return db.getHospitals().stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return db.getHospitals();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        Hospital hospital = findHospitalById(id);
        if (hospital != null) {
            return hospital.getPatients();
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        Hospital hospital = findHospitalById(id);
        if (hospital != null) {
            db.getHospitals().remove(hospital);
            return "Hospital deleted successfully";
        }
        return "Hospital not found";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return db.getHospitals().stream()
                .filter(h -> h.getAddress().equals(address))
                .collect(Collectors.toMap(Hospital::getHospitalName, h -> h));
    }
}
