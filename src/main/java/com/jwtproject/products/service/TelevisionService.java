package com.jwtproject.products.service;

import com.jwtproject.auth.dao.VendorRepository;
import com.jwtproject.exception.BadRequest400Exception;
import com.jwtproject.exception.NotFound404Exception;
import com.jwtproject.products.dao.TelevisionRepository;
import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.TelevisionDto;
import com.jwtproject.products.model.Television;
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
public class TelevisionService {

    TelevisionRepository televisionRepository;

    VendorRepository vendorRepository;

    EntityManager entityManager;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository, VendorRepository vendorRepository, EntityManager entityManager) {
        this.televisionRepository = televisionRepository;
        this.vendorRepository = vendorRepository;
        this.entityManager = entityManager;
    }

    public Television addTelevision(TelevisionDto televisionDto){
        if (vendorRepository.existsByIdAndActiveTrue(televisionDto.getVendorId())){
            Television television = new Television();
            television.setModel(televisionDto.getModel());
            television.setBrand(televisionDto.getBrand());
            television.setPrice(televisionDto.getPrice());
            television.setAvailability(televisionDto.getAvailability());
            television.setDisplayType(televisionDto.getDisplayType());
            television.setDisplaySizeInInch(televisionDto.getDisplaySizeInInch());
            television.setScreenResolution(televisionDto.getScreenResolution());
            television.setNoOfHdmiPorts(televisionDto.getNoOfHdmiPorts());
            television.setNoOfUsbPorts(televisionDto.getNoOfUsbPorts());
            television.setFeatures(televisionDto.getFeatures());
            television.setWiFi(televisionDto.isWiFi());
            television.setEthernet(televisionDto.isEthernet());
            television.setRamSizeGb(televisionDto.getRamSizeGb());
            television.setMemorySizeGb(televisionDto.getMemorySizeGb());
            television.setDisplayRefreshRate(televisionDto.getDisplayRefreshRate());
            television.setNoOfCpuCore(televisionDto.getNoOfCpuCore());
            television.setNoOfSpeakers(televisionDto.getNoOfSpeakers());
            television.setDiscountPercentage(televisionDto.getDiscountPercentage());
            television.setColour(televisionDto.getColor());
            television.setWarranty(televisionDto.getWarranty());
            television.setVendor(vendorRepository.findById(televisionDto.getVendorId()).get());
            television.setActive(true);
            televisionRepository.save(television);
            return television;
        }
        else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public List<Television> getAllByVendorId(Long id){
        if (vendorRepository.existsById(id)) {
            return televisionRepository.findByVendor_IdAndActiveTrue(id);
        }else {
            throw new NotFound404Exception("Vendor Not Found");
        }
    }

    public Television updateTelevisionById(TelevisionDto televisionDto, Long id){
        if (televisionRepository.existsByTelevisionIdAndActiveTrue(id)){
            Television television = televisionRepository.findById(id).get();
            television.setModel(televisionDto.getModel());
            television.setBrand(televisionDto.getBrand());
            television.setPrice(televisionDto.getPrice());
            television.setAvailability(televisionDto.getAvailability());
            television.setDisplayType(televisionDto.getDisplayType());
            television.setDisplaySizeInInch(televisionDto.getDisplaySizeInInch());
            television.setScreenResolution(televisionDto.getScreenResolution());
            television.setNoOfHdmiPorts(televisionDto.getNoOfHdmiPorts());
            television.setNoOfUsbPorts(televisionDto.getNoOfUsbPorts());
            television.setFeatures(televisionDto.getFeatures());
            television.setWiFi(televisionDto.isWiFi());
            television.setEthernet(televisionDto.isEthernet());
            television.setRamSizeGb(televisionDto.getRamSizeGb());
            television.setMemorySizeGb(televisionDto.getMemorySizeGb());
            television.setDisplayRefreshRate(televisionDto.getDisplayRefreshRate());
            television.setNoOfCpuCore(televisionDto.getNoOfCpuCore());
            television.setNoOfSpeakers(televisionDto.getNoOfSpeakers());
            television.setDiscountPercentage(televisionDto.getDiscountPercentage());
            television.setColour(televisionDto.getColor());
            television.setWarranty(televisionDto.getWarranty());
            television.setVendor(vendorRepository.findById(televisionDto.getVendorId()).get());
            television.setActive(true);
            televisionRepository.save(television);
            return television;
        }else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public Television getTelevisionById(Long id){
        return televisionRepository.findByTelevisionIdAndActiveTrue(id);
    }

    public String deactivateById(Long id){
        if (televisionRepository.findById(id).isPresent() && televisionRepository.findById(id).get().getActive()){
            Television television = televisionRepository.findById(id).get();
            television.setActive(false);
            televisionRepository.save(television);
            return "Deactivate television";
        }else {
            throw new NotFound404Exception("Television not found");
        }
    }

    public List<Television> getAllByModel(String model){
        return televisionRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<Television> getAllByBrand(String brand){
        return televisionRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public Map<String, Object> getFilteredTelevision(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Television> criteriaQuery = criteriaBuilder.createQuery(Television.class);
            Root<Television> acRoot = criteriaQuery.from(Television.class);
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
            List<Television> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(televisionRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(televisionRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(televisionRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(televisionRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(televisionRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }

    public List<Television> televisionSearchKeyword(String keyword, int page, int size) {
        if (size > 0) {
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Television> criteriaQuery = criteriaBuilder.createQuery(Television.class);
            Root<Television> localStoreRoot = criteriaQuery.from(Television.class);
            Predicate predicateForData = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("model")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("brand")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("colour")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("availability")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("warranty")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("displayType")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("screenResolution")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("features")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("displayRefreshRate")), "%" + keyword.toUpperCase() + "%")
            );
            criteriaQuery.select(localStoreRoot).where(predicateForData).distinct(true);
            List<Television> resource = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            return resource;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }
}
