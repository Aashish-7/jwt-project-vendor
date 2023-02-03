package com.jwtproject.products.dao;

import com.jwtproject.products.model.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WashingMachineRepository extends JpaRepository<WashingMachine, Long> {

    boolean existsByWashingMachineIdAndActiveTrue(Long washingMachineId);

    WashingMachine findByWashingMachineIdAndActiveTrue(Long washingMachineId);

    List<WashingMachine> findAllByBrandLikeAndActiveTrue(String brand);

    List<WashingMachine> findAllByModelLikeAndActiveTrue(String model);

    @Query("select distinct a.model from WashingMachine a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from WashingMachine a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from WashingMachine a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from WashingMachine a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from WashingMachine a where a.active =true")
    List<String> findAllDistinctAvailability();

    List<WashingMachine> findByVendor_IdAndActiveTrue(Long id);

    List<WashingMachine> findByActiveTrueAndVendorIdAndVendorActiveTrue(Long id);
}