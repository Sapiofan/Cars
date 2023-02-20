package com.sapiofan.cars.configs.security;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String phone;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String phone, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.phone = phone;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getRoles() {
        return roles;
    }
}
