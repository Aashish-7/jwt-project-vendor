package com.jwtproject.auth.dao;

import com.jwtproject.auth.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Vendor findByVendorEmail(String userName);

    boolean existsById(Long id);

    boolean existsByIdAndActiveTrue(Long id);
}
