package com.example.myapplication.doctor;

import com.example.myapplication.User.User;

public class Doctor  {

    private int id;
    private String username;
    private String speciality;
    private String phone;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    private String password;
    private String repassword;
    private boolean verified;

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Doctor(int id, String username, String speciality, String phone, String email, String password, String repassword, boolean verified) {
        this.id = id;
        this.username = username;
        this.speciality = speciality;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
        this.verified = verified;
    }

    public Doctor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", speciality='" + speciality + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repassword='" + repassword + '\'' +
                ", verified=" + verified +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
