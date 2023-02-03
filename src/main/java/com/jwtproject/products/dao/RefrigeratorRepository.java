package com.jwtproject.products.dao;

import com.jwtproject.products.model.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {

    boolean existsByRefrigeratorIdAndActiveTrue(Long refrigeratorId);

    Refrigerator findByRefrigeratorIdAndActiveTrue(Long refrigeratorId);

    List<Refrigerator> findAllByModelLikeAndActiveTrue(String model);

    List<Refrigerator> findAllByBrandLikeAndActiveTrue(String brand);

    @Query("select distinct a.model from Refrigerator a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from Refrigerator a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from Refrigerator a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from Refrigerator a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from Refrigerator a where a.active =true")
    List<String> findAllDistinctAvailability();

    List<Refrigerator> findByVendor_IdAndActiveTrue(Long id);

    List<Refrigerator> findByActiveTrueAndVendorIdAndVendorActiveTrue(Long id);
}