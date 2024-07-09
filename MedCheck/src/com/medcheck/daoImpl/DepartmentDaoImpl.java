package com.medcheck.daoImpl;

import com.medcheck.dao.Database;
import com.medcheck.dao.DepartmentDao;
import com.medcheck.models.Department;
import com.medcheck.models.Hospital;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentDaoImpl implements DepartmentDao {
    private Database db;

    public DepartmentDaoImpl(Database db) {
        this.db = db;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long hospitalId) {
        Hospital hospital = db.getHospitals().stream()
                .filter(h -> h.getId().equals(hospitalId))
                .findFirst()
                .orElse(null);

        if (hospital == null) {
            return List.of(); // Return an empty list if the hospital is not found
        }

        return hospital.getDepartments();
    }

    @Override
    public Department findDepartmentByName(String name) {
        return db.getDepartments().stream()
                .filter(department -> department.getDepartmentName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
