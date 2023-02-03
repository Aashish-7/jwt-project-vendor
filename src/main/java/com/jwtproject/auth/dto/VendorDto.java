package com.jwtproject.auth.dto;

public class VendorDto {

    private Long vendorId;

    private String vendorName;

    private String vendorEmail;

    private String password;

    private String currentPassword;
    private String confirmPassword;
    public void setVendorId(Long id){
        this.vendorId = id;
    }

    public Long getVendorId(){
        return vendorId;
    }

    public void setVendorName(String vendorName){
        this.vendorName = vendorName;
    }

    public String getVendorName(){
        return vendorName;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword(){
        return confirmPassword;
    }

    public void setCurrentPassword(String currentPassword){
        this.currentPassword = currentPassword;
    }

    public String getCurrentPassword(){
        return currentPassword;
    }

    public boolean validPassword(){
        if(!password.equals(confirmPassword)){
            return false;
        }
        return true;
    }
}
