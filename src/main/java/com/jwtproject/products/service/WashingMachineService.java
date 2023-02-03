package com.jwtproject.products.service;

import com.jwtproject.auth.dao.VendorRepository;
import com.jwtproject.exception.BadRequest400Exception;
import com.jwtproject.exception.NotFound404Exception;
import com.jwtproject.products.dao.WashingMachineRepository;
import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.WashingMachineDto;
import com.jwtproject.products.model.WashingMachine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WashingMachineService {

    WashingMachineRepository washingMachineRepository;

    VendorRepository vendorRepository;

    EntityManager entityManager;

    @Autowired
    public WashingMachineService(WashingMachineRepository washingMachineRepository, VendorRepository vendorRepository, EntityManager entityManager) {
        this.washingMachineRepository = washingMachineRepository;
        this.vendorRepository = vendorRepository;
        this.entityManager = entityManager;
    }

    public WashingMachine addWashingMachine(WashingMachineDto washingMachineDto){
        if (vendorRepository.existsByIdAndActiveTrue(washingMachineDto.getVendorId())){
            WashingMachine washingMachine = new WashingMachine();
            washingMachine.setModel(washingMachineDto.getModel());
            washingMachine.setBrand(washingMachineDto.getBrand());
            washingMachine.setDryer(washingMachineDto.isDryer());
            washingMachine.setFunctionType(washingMachineDto.getFunctionType());
            washingMachine.setCapacityInKg(washingMachineDto.getCapacityInKg());
            washingMachine.setPowerInStar(washingMachineDto.getPowerInStar());
            washingMachine.setTimer(washingMachineDto.isTimer());
            washingMachine.setColour(washingMachineDto.getColour());
            washingMachine.setWarranty(washingMachineDto.getWarranty());
            washingMachine.setDigitalDisplay(washingMachineDto.isDigitalDisplay());
            washingMachine.setChildLock(washingMachineDto.isChildLock());
            washingMachine.setShockProof(washingMachineDto.isShockProof());
            washingMachine.setWeight(washingMachineDto.getWeight());
            washingMachine.setDiscountPercentage(washingMachineDto.getDiscountPercentage());
            washingMachine.setAvailability(washingMachineDto.getAvailability());
            washingMachine.setPrice(washingMachineDto.getPrice());
            washingMachine.setVendor(vendorRepository.findById(washingMachineDto.getVendorId()).get());
            washingMachine.setActive(true);
            washingMachineRepository.save(washingMachine);
            return washingMachine;
        }
        else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public List<WashingMachine> getAllByVendorId(Long id){
        if (vendorRepository.existsById(id)) {
            return washingMachineRepository.findByVendor_IdAndActiveTrue(id);
        }else {
            throw new NotFound404Exception("Vendor Not Found");
        }
    }

    public WashingMachine updateWashingMachineById(WashingMachineDto washingMachineDto, Long id){
        if (washingMachineRepository.existsByWashingMachineIdAndActiveTrue(id)){
            WashingMachine washingMachine = washingMachineRepository.findById(id).get();
            washingMachine.setModel(washingMachineDto.getModel());
            washingMachine.setBrand(washingMachineDto.getBrand());
            washingMachine.setDryer(washingMachineDto.isDryer());
            washingMachine.setFunctionType(washingMachineDto.getFunctionType());
            washingMachine.setCapacityInKg(washingMachineDto.getCapacityInKg());
            washingMachine.setPowerInStar(washingMachineDto.getPowerInStar());
            washingMachine.setTimer(washingMachineDto.isTimer());
            washingMachine.setColour(washingMachineDto.getColour());
            washingMachine.setWarranty(washingMachineDto.getWarranty());
            washingMachine.setDigitalDisplay(washingMachineDto.isDigitalDisplay());
            washingMachine.setChildLock(washingMachineDto.isChildLock());
            washingMachine.setShockProof(washingMachineDto.isShockProof());
            washingMachine.setWeight(washingMachineDto.getWeight());
            washingMachine.setDiscountPercentage(washingMachineDto.getDiscountPercentage());
            washingMachine.setAvailability(washingMachineDto.getAvailability());
            washingMachine.setPrice(washingMachineDto.getPrice());
            washingMachine.setVendor(vendorRepository.findById(washingMachineDto.getVendorId()).get());
            washingMachine.setActive(true);
            washingMachineRepository.save(washingMachine);
            return washingMachine;
        } else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public WashingMachine getWashingMachineById(Long id){
        return washingMachineRepository.findByWashingMachineIdAndActiveTrue(id);
    }

    public String deactivateById(Long id){
        if (washingMachineRepository.findById(id).isPresent() && washingMachineRepository.findById(id).get().getActive()){
            WashingMachine washingMachine = washingMachineRepository.findById(id).get();
            washingMachine.setActive(false);
            washingMachineRepository.save(washingMachine);
            return "Deactivate WashingMachine";
        }else {
            throw new NotFound404Exception("Washing Machine not found!");
        }
    }

    public List<WashingMachine> getAllByModel(String model){
        return washingMachineRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<WashingMachine> getAllByBrand(String brand){
        return washingMachineRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public Map<String, Object> getFilteredWashingMachine(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<WashingMachine> criteriaQuery = criteriaBuilder.createQuery(WashingMachine.class);
            Root<WashingMachine> acRoot = criteriaQuery.from(WashingMachine.class);
            List<Predicate> predicates = new ArrayList<>();
            if (electronicFilterDto.getModel() != null && !electronicFilterDto.getModel().isEmpty()){
                predicates.add(criteriaBuilder.and(acRoot.get("model").in(electronicFilterDto.getModel())));
            }
            if (electronicFilterDto.getBrand() != null && !electronicFilterDto.getBrand().isEmpty()){
                predicates.add(criteriaBuilder.and(acRoot.get("brand").in(electronicFilterDto.getBrand())));
            }
            if (electronicFilterDto.getColour() != null && !electronicFilterDto.getColour().isEmpty()){
                predicates.add(criteriaBuilder.and(acRoot.get("colour").in(electronicFilterDto.getColour())));
            }
            if (electronicFilterDto.getAvailability() != null && !electronicFilterDto.getAvailability().isEmpty()){
                predicates.add(criteriaBuilder.and(acRoot.get("availability").in(electronicFilterDto.getAvailability())));
            }
            if (Objects.nonNull(electronicFilterDto.getPrice())){
                predicates.add(criteriaBuilder.and(acRoot.get("price").in(electronicFilterDto.getPrice())));
            }
            predicates.add(criteriaBuilder.equal(acRoot.get("active"), true));
            criteriaQuery.select(acRoot).where(predicates.toArray(new Predicate[0])).distinct(true);
            session.createQuery(criteriaQuery);
            List<WashingMachine> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(washingMachineRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(washingMachineRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(washingMachineRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(washingMachineRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(washingMachineRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }

    public List<WashingMachine> washingMachineSearchKeyword(String keyword, int page, int size) {
        if (size > 0) {
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<WashingMachine> criteriaQuery = criteriaBuilder.createQuery(WashingMachine.class);
            Root<WashingMachine> localStoreRoot = criteriaQuery.from(WashingMachine.class);
            Predicate predicateForData = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("model")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("brand")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("colour")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("availability")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("warranty")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("functionType")), "%" + keyword.toUpperCase() + "%")
            );
            criteriaQuery.select(localStoreRoot).where(predicateForData).distinct(true);
            List<WashingMachine> resource = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            return resource;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }
}
