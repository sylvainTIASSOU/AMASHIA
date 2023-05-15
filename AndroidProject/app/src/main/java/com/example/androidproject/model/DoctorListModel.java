package com.example.androidproject.model;

public class DoctorListModel
{
    private String hospitalName, service, doctorName;

    public DoctorListModel(String hospitalName, String service, String doctorName) {
        this.hospitalName = hospitalName;
        this.service = service;
        this.doctorName = doctorName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getService() {
        return service;
    }

    public String getDoctorName() {
        return doctorName;
    }
}
