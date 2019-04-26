package com.iteso.sesion9.beans;

public class User {

    private String name, password;
    private Boolean isLogged;

    public User() {
    }

    public User(String name, String password, Boolean isLogged) {
        this.name = name;
        this.password = password;
        this.isLogged = isLogged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }
    public boolean isLogged() {
        return isLogged;
    }
}
