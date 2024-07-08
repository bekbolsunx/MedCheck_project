package com.medcheck.dao;

import com.medcheck.models.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> getAllDepartmentByHospital(Long hospitalId);

    Department findDepartmentByName(String name);
}
