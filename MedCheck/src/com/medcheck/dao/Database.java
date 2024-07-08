package com.medcheck.dao;

import com.medcheck.models.Department;
import com.medcheck.models.Doctor;
import com.medcheck.models.Hospital;
import com.medcheck.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Hospital> hospitals;
    private List<Department> departments;
    private List<Doctor> doctors;
    private List<Patient> patients;

    public Database() {
        this.hospitals = new ArrayList<>();
        this.departments = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
