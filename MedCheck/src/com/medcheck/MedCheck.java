package com.medcheck;

import com.medcheck.dao.Database;
import com.medcheck.daoImpl.*;
import com.medcheck.enums.Gender;
import com.medcheck.models.*;
import com.medcheck.servicesImpl.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MedCheck {
    public static void main(String[] args) {
        // Initialize Database
        Database db = new Database();

        // Initialize DAOs
        HospitalDaoImpl hospitalDao = new HospitalDaoImpl(db);
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl(db);
        DoctorDaoImpl doctorDao = new DoctorDaoImpl(db);
        PatientDaoImpl patientDao = new PatientDaoImpl(db);
        GenericDaoImpl genericDao = new GenericDaoImpl(db);

        // Initialize Services
        HospitalServiceImpl hospitalService = new HospitalServiceImpl(hospitalDao);
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentDao);
        DoctorServiceImpl doctorService = new DoctorServiceImpl(doctorDao);
        PatientServiceImpl patientService = new PatientServiceImpl(patientDao);

        // Example Usage
        //  Add a Hospital
        Hospital hospital1 = new Hospital(1L, "KAITECH Hospital", "Osmonkula 113 Street");
        Hospital hospital2 = new Hospital(2L,"GOOGLE Hospital","Osmonkula 113 Street");
        hospitalService.addHospital(hospital1);
        hospitalService.addHospital(hospital2);

        //  Add Departments to Hospital
        Department department1 = new Department(1L, "Cardiology");
        Department department2 = new Department(2L, "Neurology");
        genericDao.add(hospital1.getId(), department1);
        genericDao.add(hospital1.getId(), department2);

        //  Add Doctors
        Doctor doctor1 = new Doctor(1L, "Isa", "Marsov", Gender.MALE, 10);
        Doctor doctor2 = new Doctor(2L, "Jannat", "Razakova", Gender.FEMALE, 8);
        genericDao.add(hospital1.getId(), doctor1);
        genericDao.add(hospital2.getId(), doctor2);

        //  Add Patients to Hospital
        Patient patient1 = new Patient(1L, "Kamila", "Jeenbekova", 35, Gender.FEMALE);
        Patient patient2 = new Patient(2L, "Arstan", "Bekov", 50, Gender.MALE);
        patientService.addPatientsToHospital(hospital1.getId(), Arrays.asList(patient1, patient2));

        //  Get All Hospitals
        List<Hospital> hospitals = hospitalService.getAllHospital();
        System.out.println("All Hospitals:");
        hospitals.forEach(h -> System.out.println(h.getHospitalName()));

        //  Get All Doctors in a KAITECH Hospital
        List<Doctor> doctorsInHospital = doctorService.getAllDoctorsByHospitalId(hospital1.getId());
        System.out.println("\nDoctors in " + hospital1.getHospitalName() + ":");
        doctorsInHospital.forEach(d -> System.out.println(d.getFirstName() + " " + d.getLastName()));

        //  Find a Doctor by ID
        Doctor foundDoctor = doctorService.findDoctorById(1L);
        if (foundDoctor != null) {
            System.out.println("\nFound Doctor: " + foundDoctor.getFirstName() + " " + foundDoctor.getLastName());
        } else {
            System.out.println("\nDoctor not found");
        }

        //  Assign Doctors to a Department
        List<Long> doctorIds = Arrays.asList(doctor1.getId(), doctor2.getId());
        String assignResult = doctorService.assignDoctorToDepartment(department1.getId(), doctorIds);
        System.out.println("\nAssign Doctors Result: " + assignResult);

        //  Get All Departments by Hospital
        List<Department> departments = departmentService.getAllDepartmentByHospital(hospital1.getId());
        System.out.println("\nDepartments in " + hospital1.getHospitalName() + ":");
        departments.forEach(d -> System.out.println(d.getDepartmentName()));

        //  Delete a Hospital
        String deleteResult = hospitalService.deleteHospitalById(hospital1.getId());
        System.out.println("\nDelete Hospital Result: " + deleteResult);

        //  Get All Hospitals by Address
        Map<String, Hospital> hospitalsByAddress = hospitalService.getAllHospitalByAddress("Osmonkula 113 Street");
        System.out.println("\nHospitals at Osmonkula 113 Street:");
        hospitalsByAddress.values().forEach(h -> System.out.println(h.getHospitalName()));
    }
}
