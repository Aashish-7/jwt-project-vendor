package com.jwtproject.products.dao;

import com.jwtproject.products.model.AC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ACRepository extends JpaRepository<AC, Long> {

    boolean existsByAcIdAndActiveTrue(Long acId);
    AC findByAcIdAndActiveTrue(Long acId);

    List<AC> findAllByModelLikeAndActiveTrue(String model);

    List<AC> findAllByBrandLikeAndActiveTrue(String brand);

    @Query("select distinct a.model from AC a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from AC a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from AC a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from AC a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from AC a where a.active =true")
    List<String> findAllDistinctAvailability();

    List<AC> findByVendor_IdAndActiveTrue(Long id);

    List<AC> findByActiveTrueAndVendorIdAndVendorActiveTrue(Long id);
}