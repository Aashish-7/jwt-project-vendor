package com.jwtproject.products.dao;

import com.jwtproject.products.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TelevisionRepository extends JpaRepository<Television, Long> {

    boolean existsByTelevisionIdAndActiveTrue(Long televisionId);

    Television findByTelevisionIdAndActiveTrue(Long televisionId);

    List<Television> findAllByModelLikeAndActiveTrue(String model);

    List<Television> findAllByBrandLikeAndActiveTrue(String brand);

    @Query("select distinct a.model from Television a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from Television a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from Television a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from Television a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from Television a where a.active =true")
    List<String> findAllDistinctAvailability();

    List<Television> findByVendor_IdAndActiveTrue(Long id);

    List<Television> findByActiveTrueAndVendorIdAndVendorActiveTrue(Long id);
}