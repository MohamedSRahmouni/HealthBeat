package com.example.myapplication.speciality;

import com.example.myapplication.doctor.Doctor;

import java.util.List;

public class Speciality {

    private int id;
    private String name;

    public Speciality(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Speciality( String name) {
        this.name = name;
    }

    public Speciality() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
