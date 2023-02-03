package com.jwtproject.products.dto;

import com.jwtproject.products.model.WashingMachine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link WashingMachine} entity
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class WashingMachineDto implements Serializable {
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
    private Long vendorId;
    private double price;
}