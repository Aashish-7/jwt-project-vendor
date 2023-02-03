package com.jwtproject.products.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwtproject.auth.model.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mobile_phone", schema = "products")
public class MobilePhone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mobile_phone_id")
    private Long mobilePhoneId;

    private String model;

    private String brand;

    private String networkConnectivity;

    private String simType;

    private String displayType;

    private String displayResolution;

    private String displaySize;

    private String os;

    private String brandUi;

    private String chipset;

    private String cpuCore;

    private String cpuClockSpeed;

    private String gpu;

    private boolean memoryCordSlotSupported;

    private String internalMemorySize;

    private int mainCameraCount;

    private String mainCameraSpecs;

    private boolean frontCamera;

    private String frontCameraSpecs;

    private String batterySize;

    private String chargingType;

    private String chargerOutput;

    private String colour;

    private int weightInGrams;

    private double price;

    private boolean bluetooth;

    private boolean gps;

    private boolean nfc;

    private boolean radio;

    private String usbType;

    private boolean audioJack;

    private boolean wlan;

    private String availability;

    private double discountPercentage;

    private Boolean active;

    private String warranty;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;
//
//    @Transient
//    private String url;
//
//    @PostLoad
//    public void postLoad(){
//        this.url = "http://localhost:8080/products/mobilePhone/"+this.mobilePhoneId+"/getMobilePhoneById";
//    }
}
