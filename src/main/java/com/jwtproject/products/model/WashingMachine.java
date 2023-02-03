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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "washing_machine", schema = "products")
public class WashingMachine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "washing_machine_id")
    private Long washingMachineId;

    private String model;

    private String brand;

    private boolean dryer;

    private String functionType;

    private double capacityInKg;

    private double powerInStar;

    private boolean timer;

    private String colour;

    private String warranty;

    private boolean digitalDisplay;

    private boolean childLock;

    private boolean shockProof;

    private double weight;

    private double discountPercentage;

    private String availability;

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
//        this.url = "http://localhost:8080/products/washingMachine/"+this.washingMachineId+"/getWashingMachineById";
//    }
}
