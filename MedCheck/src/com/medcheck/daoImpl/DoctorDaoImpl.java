package com.medcheck.daoImpl;

import com.medcheck.dao.DoctorDao;
import com.medcheck.dao.Database;
import com.medcheck.models.Doctor;
import com.medcheck.models.Hospital;
import com.medcheck.models.Department;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    Database db;

    public DoctorDaoImpl(Database db) {
        this.db = db;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        for (Hospital hospital : db.getHospitals()) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)) {
                    return doctor;
                }
            }
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : db.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(departmentId)) {
                    for (Long doctorId : doctorsId) {
                        Doctor doctor = findDoctorById(doctorId);
                        if (doctor != null) {
                            department.getDoctors().add(doctor);
                        }
                    }
                    return "Doctors assigned to department successfully";
                }
            }
        }
        return "Department not found";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        Hospital hospital = db.getHospitals().stream().filter(h -> h.getId().equals(id)).findFirst().orElse(null);
        return hospital != null ? hospital.getDoctors() : new ArrayList<>();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for (Hospital hospital : db.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)) {
                    return department.getDoctors();
                }
            }
        }
        return null;
    }
}

