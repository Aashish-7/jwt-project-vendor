package com.jwtproject.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vendor implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String vendorName;

    private String vendorEmail;

    private String password;

    private Boolean active;

    private Vendor(Long id, String vendorName, String vendorEmail, String password){
        this.id = id;
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id='" + id + "'" +
                ", vendorName='" + vendorName + '\'' +
                ", vendorEmail='" + vendorEmail + '\'' +
                ", password='" + password + '\'' +
                "}";
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setVendorName(String vendorName){
        this.vendorName = vendorName;
    }

    public String getVendorName(){
        return vendorName;
    }

    public void setVendorEmail(String vendorEmail){
        this.vendorEmail = vendorEmail;
    }

    public String getVendorEmail(){
        return vendorEmail;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return vendorEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
