package com.jwtproject.products.service;

import com.jwtproject.auth.dao.VendorRepository;
import com.jwtproject.exception.BadRequest400Exception;
import com.jwtproject.exception.NotFound404Exception;
import com.jwtproject.products.dao.ACRepository;
import com.jwtproject.products.dto.ACDto;
import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.model.AC;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ACService {

    ACRepository acRepository;

    EntityManager entityManager;

    VendorRepository vendorRepository;

    @Autowired
    public ACService(ACRepository acRepository, EntityManager entityManager, VendorRepository vendorRepository) {
        this.acRepository = acRepository;
        this.entityManager = entityManager;
        this.vendorRepository = vendorRepository;
    }

    public AC addAc(ACDto acDto) {
        if (vendorRepository.existsByIdAndActiveTrue(acDto.getVendorId())) {
            AC ac = new AC();
            ac.setModel(acDto.getModel());
            ac.setBrand(acDto.getBrand());
            ac.setColour(acDto.getColour());
            ac.setWarranty(acDto.getWarranty());
            ac.setDigitalDisplay(acDto.isDigitalDisplay());
            ac.setWeightInKg(acDto.getWeightInKg());
            ac.setDiscountPercentage(acDto.getDiscountPercentage());
            ac.setPowerInStar(acDto.getPowerInStar());
            ac.setAvailability(acDto.getAvailability());
            ac.setBuiltInStabilizer(ac.isBuiltInStabilizer());
            ac.setCapacityInTon(acDto.getCapacityInTon());
            ac.setMode(acDto.getMode());
            ac.setTimer(acDto.isTimer());
            ac.setWiFi(acDto.isWiFi());
            ac.setAirConditionerType(acDto.getAirConditionerType());
            ac.setPrice(acDto.getPrice());
            ac.setVendor(vendorRepository.findById(acDto.getVendorId()).get());
            ac.setActive(true);
            acRepository.save(ac);
            return ac;
        } else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public List<AC> getAllByVendorId(Long id) {
        if (vendorRepository.existsById(id)) {
            return acRepository.findByVendor_IdAndActiveTrue(id);
        } else {
            throw new NotFound404Exception("Vendor Not Found");
        }
    }

    public AC updateAcById(ACDto acDto, Long id) {
        if (acRepository.existsByAcIdAndActiveTrue(id)) {
            AC ac = acRepository.findById(id).get();
            if (acDto.getModel() != null) {
                ac.setModel(acDto.getModel());
            }
            if (acDto.getBrand() != null) {
                ac.setBrand(acDto.getBrand());
            }
            if (acDto.getColour() != null) {
                ac.setColour(acDto.getColour());
            }
            if (acDto.getWarranty() != null) {
                ac.setWarranty(acDto.getWarranty());
            }
            if (acDto.isDigitalDisplay()) {
                ac.setDigitalDisplay(acDto.isDigitalDisplay());
            } else {
                ac.setDigitalDisplay(acDto.isDigitalDisplay());
            }
            if (Objects.nonNull(acDto.getWeightInKg())) {
                ac.setWeightInKg(acDto.getWeightInKg());
            }
            if (Objects.nonNull(acDto.getDiscountPercentage())) {
                ac.setDiscountPercentage(acDto.getDiscountPercentage());
            }
            if (Objects.nonNull(acDto.getPowerInStar())) {
                ac.setPowerInStar(acDto.getPowerInStar());
            }
            if (acDto.getAvailability() != null) {
                ac.setAvailability(acDto.getAvailability());
            }
            if (acDto.isBuiltInStabilizer()) {
                ac.setBuiltInStabilizer(acDto.isBuiltInStabilizer());
            } else {
                ac.setBuiltInStabilizer(acDto.isBuiltInStabilizer());
            }
            if (Objects.nonNull(acDto.getCapacityInTon())) {
                ac.setCapacityInTon(acDto.getCapacityInTon());
            }
            if (acDto.getMode() != null) {
                ac.setMode(acDto.getMode());
            }
            if (acDto.isTimer()) {
                ac.setTimer(acDto.isTimer());
            } else {
                ac.setTimer(acDto.isTimer());
            }
            if (acDto.isWiFi()) {
                ac.setWiFi(acDto.isWiFi());
            } else {
                ac.setWiFi(acDto.isWiFi());
            }
            if (acDto.getAirConditionerType() != null) {
                ac.setAirConditionerType(acDto.getAirConditionerType());
            }
            if (Objects.nonNull(acDto.getPrice())) {
                ac.setPrice(acDto.getPrice());
            }
            acRepository.save(ac);
            return ac;
        } else {
            throw new NotFound404Exception("Ac not found");
        }
    }

    public AC updateAcByIdByPut(ACDto acDto, Long id) {
        if (acRepository.existsByAcIdAndActiveTrue(id)) {
            AC ac = acRepository.findById(id).get();
            ac.setModel(acDto.getModel());
            ac.setBrand(acDto.getBrand());
            ac.setColour(acDto.getColour());
            ac.setWarranty(acDto.getWarranty());
            ac.setDigitalDisplay(acDto.isDigitalDisplay());
            ac.setWeightInKg(acDto.getWeightInKg());
            ac.setDiscountPercentage(acDto.getDiscountPercentage());
            ac.setPowerInStar(acDto.getPowerInStar());
            ac.setAvailability(acDto.getAvailability());
            ac.setBuiltInStabilizer(ac.isBuiltInStabilizer());
            ac.setCapacityInTon(acDto.getCapacityInTon());
            ac.setMode(acDto.getMode());
            ac.setTimer(acDto.isTimer());
            ac.setWiFi(acDto.isWiFi());
            ac.setAirConditionerType(acDto.getAirConditionerType());
            ac.setPrice(acDto.getPrice());
            ac.setVendor(vendorRepository.findById(acDto.getVendorId()).get());
            ac.setActive(true);
            acRepository.save(ac);
            return ac;
        } else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public AC getAcById(Long id) {
        return acRepository.findByAcIdAndActiveTrue(id);
    }

    public String deactivateById(Long id) {
        if (acRepository.findById(id).isPresent() && acRepository.findById(id).get().getActive()) {
            AC ac = acRepository.findById(id).get();
            ac.setActive(false);
            acRepository.save(ac);
            return "Deactivate AC";
        } else {
            throw new NotFound404Exception("Ac not found!");
        }
    }


    public List<AC> getAllByModel(String model) {
        return acRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<AC> getAllByBrand(String brand) {
        return acRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public Map<String, Object> getFilteredAc(int page, int size, ElectronicFilterDto electronicFilterDto) {
        Map<String, Object> map = new HashMap<>();
        if (size != 0) {
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AC> criteriaQuery = criteriaBuilder.createQuery(AC.class);
            Root<AC> acRoot = criteriaQuery.from(AC.class);
            List<Predicate> predicates = new ArrayList<>();
            if (electronicFilterDto.getModel() != null && !electronicFilterDto.getModel().isEmpty()) {
                predicates.add(criteriaBuilder.and(acRoot.get("model").in(electronicFilterDto.getModel())));
            }
            if (electronicFilterDto.getBrand() != null && !electronicFilterDto.getBrand().isEmpty()) {
                predicates.add(criteriaBuilder.and(acRoot.get("brand").in(electronicFilterDto.getBrand())));
            }
            if (electronicFilterDto.getColour() != null && !electronicFilterDto.getColour().isEmpty()) {
                predicates.add(criteriaBuilder.and(acRoot.get("colour").in(electronicFilterDto.getColour())));
            }
            if (electronicFilterDto.getAvailability() != null && !electronicFilterDto.getAvailability().isEmpty()) {
                predicates.add(criteriaBuilder.and(acRoot.get("availability").in(electronicFilterDto.getAvailability())));
            }
            if (Objects.nonNull(electronicFilterDto.getPrice())) {
                predicates.add(criteriaBuilder.and(acRoot.get("price").in(electronicFilterDto.getPrice())));
            }
            predicates.add(criteriaBuilder.equal(acRoot.get("active"), true));
            criteriaQuery.select(acRoot).where(predicates.toArray(new Predicate[0])).distinct(true);
            session.createQuery(criteriaQuery);
            List<AC> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(acRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(acRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(acRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(acRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(acRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }

    public List<AC> acSearchKeyword(String keyword, int page, int size) {
        if (size > 0){
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AC> criteriaQuery = criteriaBuilder.createQuery(AC.class);
        Root<AC> root = criteriaQuery.from(AC.class);
        Predicate predicateForData = criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.upper(root.get("model")), "%" + keyword.toUpperCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.upper(root.get("brand")), "%" + keyword.toUpperCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.upper(root.get("colour")), "%" + keyword.toUpperCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.upper(root.get("availability")), "%" + keyword.toUpperCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.upper(root.get("warranty")), "%" + keyword.toUpperCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.upper(root.get("airConditionerType")), "%" + keyword.toUpperCase() + "%"),
                criteriaBuilder.like(criteriaBuilder.upper(root.get("mode")), "%" + keyword.toUpperCase() + "%")
        );
        criteriaQuery.select(root).where(predicateForData).distinct(true);
        List<AC> resource = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
        return resource;
    } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public List<AC> getAllACByVendor(){
        return acRepository.findAll();
    }
}
