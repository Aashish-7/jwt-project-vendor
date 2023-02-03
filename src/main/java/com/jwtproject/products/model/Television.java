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
@Table(name = "television", schema = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Television implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "television_id")
    private Long televisionId;

    private String model;

    private String brand;

    private double price;

    private String availability;

    private String displayType;

    private int displaySizeInInch;

    private String screenResolution;

    private int noOfHdmiPorts;

    private int noOfUsbPorts;

    private String features;

    private boolean wiFi;

    private boolean ethernet;

    private double ramSizeGb;

    private double memorySizeGb;

    private String displayRefreshRate;

    private int noOfCpuCore;

    private int noOfSpeakers;

    private String colour;

    private double discountPercentage;

    private String warranty;

    private Boolean active;

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
//        this.url = "http://localhost:8080/products/television/"+this.televisionId+"/getTelevisionById";
//    }
}
