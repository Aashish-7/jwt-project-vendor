package com.jwtproject.products.service;

import com.jwtproject.auth.dao.VendorRepository;
import com.jwtproject.exception.BadRequest400Exception;
import com.jwtproject.exception.NotFound404Exception;
import com.jwtproject.products.dao.LaptopRepository;
import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.LaptopDto;
import com.jwtproject.products.model.Laptop;
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
public class LaptopService {

    LaptopRepository laptopRepository;

    VendorRepository vendorRepository;

    EntityManager entityManager;

    @Autowired
    public LaptopService(LaptopRepository laptopRepository, VendorRepository vendorRepository, EntityManager entityManager) {
        this.laptopRepository = laptopRepository;
        this.vendorRepository = vendorRepository;
        this.entityManager = entityManager;
    }


    public Laptop addLaptop(LaptopDto laptopDto){
        if (vendorRepository.existsByIdAndActiveTrue(laptopDto.getVendorId())) {
            Laptop laptop = new Laptop();
            laptop.setModel(laptopDto.getModel());
            laptop.setBrand(laptopDto.getBrand());
            laptop.setPrice(laptopDto.getPrice());
            laptop.setWeightInKg(laptopDto.getWeightInKg());
            laptop.setScreenSizeInInch(laptopDto.getScreenSizeInInch());
            laptop.setTouchScreen(laptopDto.isTouchScreen());
            laptop.setAvailability(laptopDto.getAvailability());
            laptop.setScreenResolution(laptopDto.getScreenResolution());
            laptop.setCpuBrand(laptopDto.getCpuBrand());
            laptop.setCpuModel(laptopDto.getCpuModel());
            laptop.setCpuGeneration(laptopDto.getCpuGeneration());
            laptop.setCpuClockSpeed(laptopDto.getCpuClockSpeed());
            laptop.setNoOfCpuCore(laptopDto.getNoOfCpuCore());
            laptop.setHardDiskSize(laptopDto.getHardDiskSize());
            laptop.setRamSize(laptopDto.getRamSize());
            laptop.setRamType(laptopDto.getRamType());
            laptop.setFingerprint(laptopDto.isFingerprint());
            laptop.setOs(laptopDto.getOs());
            laptop.setWarranty(laptopDto.getWarranty());
            laptop.setBatteryBackupHour(laptopDto.getBatteryBackupHour());
            laptop.setGraphicCard(laptopDto.getGraphicCard());
            laptop.setNoOfUsbPorts(laptopDto.getNoOfUsbPorts());
            laptop.setEthernetPort(laptopDto.isEthernetPort());
            laptop.setBluetooth(laptopDto.isBluetooth());
            laptop.setHeadphoneJack(laptopDto.isHeadphoneJack());
            laptop.setHdmiPort(laptopDto.isHdmiPort());
            laptop.setColour(laptopDto.getColor());
            laptop.setDiscountPercentage(laptopDto.getDiscountPercentage());
            laptop.setNoOfSpeaker(laptopDto.getNoOfSpeaker());
            laptop.setVendor(vendorRepository.findById(laptopDto.getVendorId()).get());
            laptop.setActive(true);
            laptopRepository.save(laptop);
            return laptop;
        }
        else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public List<Laptop> getAllByVendorId(Long id) {
        if (vendorRepository.existsById(id)) {
            return laptopRepository.findByVendor_IdAndActiveTrue(id);
        } else {
            throw new NotFound404Exception("Vendor Not Found");
        }
    }

    public Laptop updateLaptopById(LaptopDto laptopDto, Long id){
        if (laptopRepository.existsByLaptopIdAndActiveTrue(id)){
            Laptop laptop = laptopRepository.findById(id).get();
            laptop.setModel(laptopDto.getModel());
            laptop.setBrand(laptopDto.getBrand());
            laptop.setPrice(laptopDto.getPrice());
            laptop.setWeightInKg(laptopDto.getWeightInKg());
            laptop.setScreenSizeInInch(laptopDto.getScreenSizeInInch());
            laptop.setTouchScreen(laptopDto.isTouchScreen());
            laptop.setAvailability(laptopDto.getAvailability());
            laptop.setScreenResolution(laptopDto.getScreenResolution());
            laptop.setCpuBrand(laptopDto.getCpuBrand());
            laptop.setCpuModel(laptopDto.getCpuModel());
            laptop.setCpuGeneration(laptopDto.getCpuGeneration());
            laptop.setCpuClockSpeed(laptopDto.getCpuClockSpeed());
            laptop.setNoOfCpuCore(laptopDto.getNoOfCpuCore());
            laptop.setHardDiskSize(laptopDto.getHardDiskSize());
            laptop.setRamSize(laptopDto.getRamSize());
            laptop.setRamType(laptopDto.getRamType());
            laptop.setFingerprint(laptopDto.isFingerprint());
            laptop.setOs(laptopDto.getOs());
            laptop.setWarranty(laptopDto.getWarranty());
            laptop.setBatteryBackupHour(laptopDto.getBatteryBackupHour());
            laptop.setGraphicCard(laptopDto.getGraphicCard());
            laptop.setNoOfUsbPorts(laptopDto.getNoOfUsbPorts());
            laptop.setEthernetPort(laptopDto.isEthernetPort());
            laptop.setBluetooth(laptopDto.isBluetooth());
            laptop.setHeadphoneJack(laptopDto.isHeadphoneJack());
            laptop.setHdmiPort(laptopDto.isHdmiPort());
            laptop.setColour(laptopDto.getColor());
            laptop.setDiscountPercentage(laptopDto.getDiscountPercentage());
            laptop.setNoOfSpeaker(laptopDto.getNoOfSpeaker());
            laptop.setVendor(vendorRepository.findById(laptopDto.getVendorId()).get());
            laptop.setActive(true);
            laptopRepository.save(laptop);
            return laptop;
        } else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public Laptop getLaptopById(Long id){
        return laptopRepository.findByLaptopIdAndActiveTrue(id);
    }

    public String deactivateById(Long id){
        if (laptopRepository.findById(id).isPresent() && laptopRepository.findById(id).get().getActive()){
            Laptop laptop = laptopRepository.findById(id).get();
            laptop.setActive(false);
            laptopRepository.save(laptop);
            return "Deactivate laptop";
        }else {
            throw new NotFound404Exception("Laptop not found.");
        }
    }

    public List<Laptop> getAllByModel(String model){
        return laptopRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<Laptop> getAllByBrand(String brand){
        return laptopRepository.findAllByBrandLikeAndActiveTrue(brand);
    }


    public Map<String, Object> getFilteredLaptop(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Laptop> criteriaQuery = criteriaBuilder.createQuery(Laptop.class);
            Root<Laptop> acRoot = criteriaQuery.from(Laptop.class);
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
            List<Laptop> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(laptopRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(laptopRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(laptopRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(laptopRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(laptopRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }

    public List<Laptop> laptopSearchKeyword(String keyword, int page, int size){
        if (size > 0) {
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Laptop> criteriaQuery = criteriaBuilder.createQuery(Laptop.class);
            Root<Laptop> localStoreRoot = criteriaQuery.from(Laptop.class);
            Predicate predicateForData = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("brand")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("model")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("colour")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("availability")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("warranty")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("screenResolution")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("cpuBrand")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("cpuModel")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("cpuGeneration")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("cpuClockSpeed")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("hardDiskSize")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("ramSize")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("ramType")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("os")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("batteryBackupHour")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("graphicCard")), "%" + keyword.toUpperCase() + "%")
            );
            criteriaQuery.select(localStoreRoot).where(predicateForData).distinct(true);
            List<Laptop> resource = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            return resource;        }
        else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }
}
