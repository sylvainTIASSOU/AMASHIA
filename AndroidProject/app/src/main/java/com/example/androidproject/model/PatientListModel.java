package com.example.androidproject.model;

public class PatientListModel
{
    private String fullName, lastMesssage;

    public PatientListModel(String fullName, String lastMesssage) {
        this.fullName = fullName;
        this.lastMesssage = lastMesssage;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastMesssage() {
        return lastMesssage;
    }

    public void setLastMesssage(String lastMesssage) {
        this.lastMesssage = lastMesssage;
    }
}
