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
@Table(name = "ac", schema = "products")
public class AC implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_id")
    private Long acId;

    private String model;

    private String brand;

    private String colour;

    private String warranty;

    private boolean digitalDisplay;

    private double weightInKg;

    private double discountPercentage;

    private double powerInStar;

    private String availability;

    private boolean builtInStabilizer;

    private double capacityInTon;

    private String mode;

    private boolean timer;

    private boolean wiFi;

    private String airConditionerType;

    private Boolean active;

    private double price;

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
//        this.url = "http://localhost:8080/products/ac/"+this.acId+"/getAcById";
//    }
}
