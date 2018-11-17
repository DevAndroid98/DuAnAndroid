package com.tinhduchung.dev.poly.duanandroid.user;

public class User {
    private String name;
    private String phone;
    private String uri;
    private String address;
    private String email;
    private String gender;


    public User() {
    }

    public User(String name, String phone, String uri, String address, String email, String gender) {
        this.name = name;
        this.phone = phone;
        this.uri = uri;
        this.address = address;
        this.email = email;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
