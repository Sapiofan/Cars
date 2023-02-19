package com.sapiofan.cars.entities.ui;

public class UserUI {

    private String phone;

    private String password;

    public UserUI() {
    }

    public UserUI(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
