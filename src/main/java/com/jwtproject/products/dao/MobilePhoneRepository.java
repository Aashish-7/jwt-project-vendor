package com.jwtproject.products.dao;

import com.jwtproject.products.model.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MobilePhoneRepository extends JpaRepository<MobilePhone, Long> {


    boolean existsByMobilePhoneIdAndActiveTrue(Long mobilePhoneId);

    MobilePhone findByMobilePhoneIdAndActiveTrue(Long mobilePhoneId);

    List<MobilePhone> findAllByModelLikeAndActiveTrue(String model);

    List<MobilePhone> findAllByBrandLikeAndActiveTrue(String brand);

    @Query("select distinct a.model from MobilePhone a where a.active =true")
    List<String> findAllDistinctModel();

    @Query("select distinct  a.brand from MobilePhone a where a.active =true")
    List<String> findAllDistinctBrand();

    @Query("select distinct a.colour from MobilePhone a where a.active =true")
    List<String> findAllDistinctColour();

    @Query("select distinct  a.price from MobilePhone a where a.active =true")
    List<Double> findAllDistinctPrice();

    @Query("select distinct a.availability from MobilePhone a where a.active =true")
    List<String> findAllDistinctAvailability();

    List<MobilePhone> findByVendor_IdAndActiveTrue(Long id);

    List<MobilePhone> findByActiveTrueAndVendorIdAndVendorActiveTrue(Long id);
}