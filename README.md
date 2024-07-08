# MedCheck

MedCheck is a Java-based application designed to manage hospital data, including hospitals, departments, doctors, and patients.
It provides functionalities to add, retrieve, update, and delete records, facilitating efficient healthcare management.

## Overview

MedCheck is built to streamline hospital management processes. It allows users to manage multiple hospitals, each with their respective 
departments, doctors, and patients. The application is structured with a focus on modularity, ensuring that each entity (Hospital, Department,
Doctor, Patient) has dedicated services and data access objects (DAOs) for efficient data handling and manipulation.

## Project Structure

MedCheck/
├── .idea/
├── out/
├── src/
│ └── com/
│ └── medcheck/
│ ├── enums/
│ │ └── Gender.java
│ ├── models/
│ │ ├── Hospital.java
│ │ ├── Department.java
│ │ ├── Doctor.java
│ │ └── Patient.java
│ ├── dao/
│ │ ├── Database.java
│ │ ├── HospitalDao.java
│ │ ├── GenericDao.java
│ │ ├── DepartmentDao.java
│ │ ├── DoctorDao.java
│ │ └── PatientDao.java
│ ├── daoImpl/
│ │ ├── HospitalDaoImpl.java
│ │ ├── GenericDaoImpl.java
│ │ ├── DepartmentDaoImpl.java
│ │ ├── DoctorDaoImpl.java
│ │ └── PatientDaoImpl.java
│ ├── services/
│ │ ├── HospitalService.java
│ │ ├── DepartmentService.java
│ │ ├── DoctorService.java
│ │ └── PatientService.java
│ ├── servicesImpl/
│ │ ├── HospitalServiceImpl.java
│ │ ├── DepartmentServiceImpl.java
│ │ ├── DoctorServiceImpl.java
│ │ └── PatientServiceImpl.java
│ └── MedCheck.java
├── .gitignore
└── README.md
