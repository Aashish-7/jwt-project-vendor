package com.jwtproject.auth.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private String vendorEmail;

    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String vendorEmail, String password) {
        this.vendorEmail = vendorEmail;
        this.password = password;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
