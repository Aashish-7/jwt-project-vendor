package com.jwtproject.products.service;

import com.jwtproject.auth.dao.VendorRepository;
import com.jwtproject.exception.BadRequest400Exception;
import com.jwtproject.exception.NotFound404Exception;
import com.jwtproject.products.dao.RefrigeratorRepository;
import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.RefrigeratorDto;
import com.jwtproject.products.model.Refrigerator;
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
public class RefrigeratorService {

    RefrigeratorRepository refrigeratorRepository;

    VendorRepository vendorRepository;

    EntityManager entityManager;

    @Autowired
    public RefrigeratorService(RefrigeratorRepository refrigeratorRepository, VendorRepository vendorRepository, EntityManager entityManager) {
        this.refrigeratorRepository = refrigeratorRepository;
        this.vendorRepository = vendorRepository;
        this.entityManager = entityManager;
    }

    public Refrigerator addRefrigerator(RefrigeratorDto refrigeratorDto){
        if (vendorRepository.existsByIdAndActiveTrue(refrigeratorDto.getVendorId())){
            Refrigerator refrigerator = new Refrigerator();
            refrigerator.setModel(refrigeratorDto.getModel());
            refrigerator.setBrand(refrigeratorDto.getBrand());
            refrigerator.setColour(refrigeratorDto.getColour());
            refrigerator.setWarranty(refrigeratorDto.getWarranty());
            refrigerator.setDigitalDisplay(refrigeratorDto.isDigitalDisplay());
            refrigerator.setWeight(refrigeratorDto.getWeight());
            refrigerator.setDiscountPercentage(refrigeratorDto.getDiscountPercentage());
            refrigerator.setPowerInStar(refrigeratorDto.getPowerInStar());
            refrigerator.setCapacityInLitre(refrigeratorDto.getCapacityInLitre());
            refrigerator.setMultiDoor(refrigeratorDto.isMultiDoor());
            refrigerator.setFreezerPosition(refrigeratorDto.getFreezerPosition());
            refrigerator.setAvailability(refrigeratorDto.getAvailability());
            refrigerator.setBuiltInStabilizer(refrigeratorDto.isBuiltInStabilizer());
            refrigerator.setPrice(refrigeratorDto.getPrice());
            refrigerator.setVendor(vendorRepository.findById(refrigeratorDto.getVendorId()).get());
            refrigerator.setActive(true);
            refrigeratorRepository.save(refrigerator);
            return refrigerator;
        }
        else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public List<Refrigerator> getAllByVendorId(Long id){
        if (vendorRepository.existsById(id)) {
            return refrigeratorRepository.findByVendor_IdAndActiveTrue(id);
        }else {
            throw new NotFound404Exception("Vendor Not Found");
        }
    }

    public Refrigerator updateRefrigeratorById(RefrigeratorDto refrigeratorDto, Long id){
        if (refrigeratorRepository.existsByRefrigeratorIdAndActiveTrue(id)){
            Refrigerator refrigerator = refrigeratorRepository.findById(id).get();
            refrigerator.setModel(refrigeratorDto.getModel());
            refrigerator.setBrand(refrigeratorDto.getBrand());
            refrigerator.setColour(refrigeratorDto.getColour());
            refrigerator.setWarranty(refrigeratorDto.getWarranty());
            refrigerator.setDigitalDisplay(refrigeratorDto.isDigitalDisplay());
            refrigerator.setWeight(refrigeratorDto.getWeight());
            refrigerator.setDiscountPercentage(refrigeratorDto.getDiscountPercentage());
            refrigerator.setPowerInStar(refrigeratorDto.getPowerInStar());
            refrigerator.setCapacityInLitre(refrigeratorDto.getCapacityInLitre());
            refrigerator.setMultiDoor(refrigeratorDto.isMultiDoor());
            refrigerator.setFreezerPosition(refrigeratorDto.getFreezerPosition());
            refrigerator.setAvailability(refrigeratorDto.getAvailability());
            refrigerator.setBuiltInStabilizer(refrigeratorDto.isBuiltInStabilizer());
            refrigerator.setPrice(refrigeratorDto.getPrice());
            refrigerator.setVendor(vendorRepository.findById(refrigeratorDto.getVendorId()).get());
            refrigerator.setActive(true);
            refrigeratorRepository.save(refrigerator);
            return refrigerator;
        } else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public Refrigerator getRefrigeratorById(Long id){
        return refrigeratorRepository.findByRefrigeratorIdAndActiveTrue(id);
    }


    public String deactivateById(Long id){
        if (refrigeratorRepository.findById(id).isPresent() && refrigeratorRepository.findById(id).get().getActive()){
            Refrigerator refrigerator =refrigeratorRepository.findById(id).get();
            refrigerator.setActive(false);
            refrigeratorRepository.save(refrigerator);
            return "Deactivate refrigerator";
        }else {
            throw new NotFound404Exception("Mobile not found");
        }
    }

    public List<Refrigerator> getAllByModel(String model){
        return refrigeratorRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<Refrigerator> getAllByBrand(String brand){
        return refrigeratorRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public Map<String, Object> getFilteredRefrigerator(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Refrigerator> criteriaQuery = criteriaBuilder.createQuery(Refrigerator.class);
            Root<Refrigerator> acRoot = criteriaQuery.from(Refrigerator.class);
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
            List<Refrigerator> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(refrigeratorRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(refrigeratorRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(refrigeratorRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(refrigeratorRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(refrigeratorRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }

    public List<Refrigerator> refrigeratorSearchKeyword(String keyword, int page, int size) {
        if (size > 0) {
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Refrigerator> criteriaQuery = criteriaBuilder.createQuery(Refrigerator.class);
            Root<Refrigerator> localStoreRoot = criteriaQuery.from(Refrigerator.class);
            Predicate predicateForData = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("model")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("brand")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("colour")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("availability")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("warranty")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("freezerPosition")), "%" + keyword.toUpperCase() + "%")
            );
            criteriaQuery.select(localStoreRoot).where(predicateForData).distinct(true);
            List<Refrigerator> resource = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            return resource;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }
}
