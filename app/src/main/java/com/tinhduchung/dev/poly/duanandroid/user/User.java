package com.tinhduchung.dev.poly.duanandroid.user;

public class User {
    private String name;
    private String phone;
    private String uri;

    public User() {
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

    public User(String name, String phone, String uri) {

        this.name = name;
        this.phone = phone;
        this.uri = uri;
    }
}
