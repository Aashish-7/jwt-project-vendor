package com.jwtproject.auth.service;

import com.jwtproject.auth.dao.VendorRepository;
import com.jwtproject.auth.dto.VendorDto;
import com.jwtproject.auth.model.Vendor;
import com.jwtproject.exception.BadRequest400Exception;
import com.jwtproject.products.dao.*;
import com.jwtproject.products.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements UserDetailsService {

    VendorRepository vendorRepository;

    BCryptPasswordEncoder passwordEncoder;

    EntityManager entityManager;

    ACRepository acRepository;

    LaptopRepository laptopRepository;

    MobilePhoneRepository mobilePhoneRepository;

    RefrigeratorRepository refrigeratorRepository;

    TelevisionRepository televisionRepository;

    WashingMachineRepository washingMachineRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, BCryptPasswordEncoder passwordEncoder, EntityManager entityManager) {
        this.vendorRepository = vendorRepository;
        this.passwordEncoder = passwordEncoder;
        this.entityManager = entityManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Vendor vendor = vendorRepository.findByVendorEmail(username);
        if (vendor == null){
            throw new UsernameNotFoundException("Vendor with " + username + "does not exists");
        }
        return vendor;
    }

    public Vendor addVendor(VendorDto vendorDto){
        Vendor vendor = new Vendor();
        vendor.setVendorName(vendorDto.getVendorName());
        vendor.setVendorEmail(vendorDto.getVendorEmail());
        vendor.setPassword(passwordEncoder.encode(vendorDto.getPassword()));
        return vendorRepository.save(vendor);
    }

    public String changePassword(VendorDto vendorDto, Long id) throws Exception {
        if (vendorDto.getCurrentPassword().equals(vendorDto.getPassword())) {
            return "current password and new password are same";
        }
        if (passwordEncoder.matches(vendorDto.getCurrentPassword(), vendorRepository.findById(id).get().getPassword()) && vendorDto.getPassword().equals(vendorDto.getConfirmPassword())) {
            Vendor vendor = vendorRepository.findById(id).get();
            vendor.setPassword(passwordEncoder.encode(vendorDto.getPassword()));
            vendorRepository.save(vendor);
            return "Password changed successfully";
        } else {
            return "Enter valid password";
        }
    }

    public String updateVendor(VendorDto vendorDto, Long id){
        if (vendorRepository.existsById(id)) {
            Vendor vendor = vendorRepository.findById(id).get();
            vendor.setVendorName(vendorDto.getVendorName());
            vendor.setVendorEmail(vendorDto.getVendorEmail());
            vendorRepository.save(vendor);
        } else {
            System.out.println("Vendor not found");
        }
        return "vendor successfully update";
    }

    public Boolean deleteVendor(){

        return Boolean.TRUE;
    }

    public List<Vendor> vendorSearchKeyword(String keyword, int page, int size) {
        if (size > 0) {
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Vendor> criteriaQuery = criteriaBuilder.createQuery(Vendor.class);
            Root<Vendor> vendorRoot = criteriaQuery.from(Vendor.class);
            Predicate predicateForData = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.upper(vendorRoot.get("vendorName")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(vendorRoot.get("vendorEmail")), "%" + keyword.toUpperCase() + "%"));
//                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("city")), "%" + keyword.toUpperCase() + "%"),
//                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("ownerName")), "%" + keyword.toUpperCase() + "%"),
//                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("description")), "%" + keyword.toUpperCase() + "%"));
            criteriaQuery.select(vendorRoot).where(predicateForData).distinct(true);
            List<Vendor> resource = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            return resource;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public List<AC> getAllAcByVendorId(Long id){
        return acRepository.findByActiveTrueAndVendorIdAndVendorActiveTrue(id);
    }

    public List<Laptop> getAllLaptopByVendorId(Long id){
        return laptopRepository.findByActiveTrueAndVendorIdAndVendorActiveTrue(id);
    }

    public List<MobilePhone> getAllMobilePhoneByVendorId(Long id){
        return mobilePhoneRepository.findByActiveTrueAndVendorIdAndVendorActiveTrue(id);
    }

    public List<Refrigerator> getAllRefrigeratorByVendorId(Long id){
        return refrigeratorRepository.findByActiveTrueAndVendorIdAndVendorActiveTrue(id);
    }

    public List<Television> getAllTelevisionByVendorId(Long id){
        return televisionRepository.findByActiveTrueAndVendorIdAndVendorActiveTrue(id);
    }

    public List<WashingMachine> getAllWashingMachineByVendorId(Long id){
        return washingMachineRepository.findByActiveTrueAndVendorIdAndVendorActiveTrue(id);
    }
}
