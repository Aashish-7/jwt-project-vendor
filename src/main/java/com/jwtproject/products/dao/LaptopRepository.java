package com.jwtproject.products.dao;

import com.jwtproject.products.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    boolean existsByLaptopIdAndActiveTrue(Long laptopId);

    Laptop findByLaptopIdAndActiveTrue(Long laptopId);

    List<Laptop> findAllByBrandLikeAndActiveTrue(String brand);

    List<Laptop> findAllByModelLikeAndActiveTrue(String model);

    @Query("select distinct a.model from Laptop a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from Laptop a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from Laptop a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from Laptop a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from Laptop a where a.active =true")
    List<String> findAllDistinctAvailability();

    List<Laptop> findByVendor_IdAndActiveTrue(Long id);
    List<Laptop> findByActiveTrueAndVendorIdAndVendorActiveTrue(Long id);
}