package com.medcheck.daoImpl;

import com.medcheck.dao.Database;
import com.medcheck.dao.GenericDao;
import com.medcheck.models.Department;
import com.medcheck.models.Doctor;
import com.medcheck.models.Hospital;
import com.medcheck.models.Patient;

import java.util.ArrayList;

public class GenericDaoImpl<T> implements GenericDao<T> {
    private Database db;

    public GenericDaoImpl(Database db) {
        this.db = db;
    }

    @Override
    public String add(Long hospitalId, T t) {
        Hospital hospital = db.getHospitals().stream()
                .filter(h -> h.getId().equals(hospitalId))
                .findFirst()
                .orElse(null);

        if (hospital == null) {
            return "Hospital not found";
        }

        if (t instanceof Department) {
            if (hospital.getDepartments() == null) {
                hospital.setDepartments(new ArrayList<>());
            }
            hospital.getDepartments().add((Department) t);
            db.getDepartments().add((Department) t); // Add to the database list as well
            return "Department added successfully";
        } else if (t instanceof Doctor) {
            if (hospital.getDoctors() == null) {
                hospital.setDoctors(new ArrayList<>());
            }
            hospital.getDoctors().add((Doctor) t);
            db.getDoctors().add((Doctor) t); // Add to the database list as well
            return "Doctor added successfully";
        } else if (t instanceof Patient) {
            if (hospital.getPatients() == null) {
                hospital.setPatients(new ArrayList<>());
            }
            hospital.getPatients().add((Patient) t);
            db.getPatients().add((Patient) t); // Add to the database list as well
            return "Patient added successfully";
        }

        return "Unknown type";
    }

    @Override
    public void removeById(Long id) {
        // Implement remove logic based on type
    }

    @Override
    public String updateById(Long id, T t) {
        // Implement update logic based on type
        return null;
    }
}
