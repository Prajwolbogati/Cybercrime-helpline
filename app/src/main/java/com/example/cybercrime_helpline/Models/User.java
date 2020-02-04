package com.example.cybercrime_helpline.Models;

public class User {
    private  String fullname;
    private String  citizenid;
    private String fathername;
    private String number;
    private  String email_address;
    private  String username;
    private  String password;

    public User(String fullname, String citizenid, String fathername, String number, String email_address, String username, String password) {
        this.fullname = fullname;
        this.citizenid = citizenid;
        this.fathername = fathername;
        this.number = number;
        this.email_address = email_address;
        this.username = username;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCitizenid() {
        return citizenid;
    }

    public void setCitizenid(String citizenid) {
        this.citizenid = citizenid;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullname='" + fullname + '\'' +
                ", citizenid='" + citizenid + '\'' +
                ", fathername='" + fathername + '\'' +
                ", number='" + number + '\'' +
                ", email_address='" + email_address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
