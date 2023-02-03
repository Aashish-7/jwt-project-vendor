package com.jwtproject.products.service;

import com.jwtproject.auth.dao.VendorRepository;
import com.jwtproject.exception.BadRequest400Exception;
import com.jwtproject.exception.NotFound404Exception;
import com.jwtproject.products.dao.MobilePhoneRepository;
import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.MobilePhoneDto;
import com.jwtproject.products.model.MobilePhone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MobilePhoneService {

    MobilePhoneRepository mobilePhoneRepository;

    VendorRepository vendorRepository;

    EntityManager entityManager;

    @Autowired
    public MobilePhoneService(MobilePhoneRepository mobilePhoneRepository, VendorRepository vendorRepository, EntityManager entityManager) {
        this.mobilePhoneRepository = mobilePhoneRepository;
        this.vendorRepository = vendorRepository;
        this.entityManager = entityManager;
    }

    public MobilePhone addMobilePhone(MobilePhoneDto mobilePhoneDto){
        if (vendorRepository.existsByIdAndActiveTrue(mobilePhoneDto.getVendorId())){
            MobilePhone mobilePhone = new MobilePhone();
            mobilePhone.setModel(mobilePhoneDto.getModel());
            mobilePhone.setBrand(mobilePhoneDto.getBrand());
            mobilePhone.setNetworkConnectivity(mobilePhoneDto.getNetworkConnectivity());
            mobilePhone.setSimType(mobilePhoneDto.getSimType());
            mobilePhone.setDisplayType(mobilePhoneDto.getDisplayType());
            mobilePhone.setDisplayResolution(mobilePhoneDto.getDisplayResolution());
            mobilePhone.setDisplaySize(mobilePhoneDto.getDisplaySize());
            mobilePhone.setOs(mobilePhoneDto.getOs());
            mobilePhone.setBrandUi(mobilePhoneDto.getBrandUi());
            mobilePhone.setChipset(mobilePhoneDto.getChipset());
            mobilePhone.setCpuCore(mobilePhoneDto.getCpuCore());
            mobilePhone.setCpuClockSpeed(mobilePhoneDto.getCpuClockSpeed());
            mobilePhone.setGpu(mobilePhoneDto.getGpu());
            mobilePhone.setMemoryCordSlotSupported(mobilePhoneDto.isMemoryCordSlotSupported());
            mobilePhone.setInternalMemorySize(mobilePhoneDto.getInternalMemorySize());
            mobilePhone.setMainCameraCount(mobilePhoneDto.getMainCameraCount());
            mobilePhone.setMainCameraSpecs(mobilePhoneDto.getMainCameraSpecs());
            mobilePhone.setFrontCamera(mobilePhoneDto.isFrontCamera());
            mobilePhone.setFrontCameraSpecs(mobilePhoneDto.getFrontCameraSpecs());
            mobilePhone.setBatterySize(mobilePhoneDto.getBatterySize());
            mobilePhone.setChargingType(mobilePhoneDto.getChargingType());
            mobilePhone.setChargerOutput(mobilePhoneDto.getChargerOutput());
            mobilePhone.setColour(mobilePhoneDto.getColor());
            mobilePhone.setWeightInGrams(mobilePhoneDto.getWeightInGrams());
            mobilePhone.setPrice(mobilePhoneDto.getPrice());
            mobilePhone.setBluetooth(mobilePhoneDto.isBluetooth());
            mobilePhone.setGps(mobilePhoneDto.isGps());
            mobilePhone.setNfc(mobilePhoneDto.isNfc());
            mobilePhone.setRadio(mobilePhoneDto.isRadio());
            mobilePhone.setUsbType(mobilePhoneDto.getUsbType());
            mobilePhone.setAudioJack(mobilePhoneDto.isAudioJack());
            mobilePhone.setWlan(mobilePhoneDto.isWlan());
            mobilePhone.setAvailability(mobilePhoneDto.getAvailability());
            mobilePhone.setDiscountPercentage(mobilePhoneDto.getDiscountPercentage());
            mobilePhone.setVendor(vendorRepository.findById(mobilePhoneDto.getVendorId()).get());
            mobilePhone.setActive(true);
            mobilePhoneRepository.save(mobilePhone);
            return mobilePhone;
        }
        else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public List<MobilePhone> getAllByVendorId(Long id){
        if (vendorRepository.existsById(id)) {
            return mobilePhoneRepository.findByVendor_IdAndActiveTrue(id);
        }else {
            throw new NotFound404Exception("Vendor Not Found");
        }
    }

    public MobilePhone updateMobilePhoneById(MobilePhoneDto mobilePhoneDto, Long id){
        if (mobilePhoneRepository.existsByMobilePhoneIdAndActiveTrue(id)){
            MobilePhone mobilePhone = mobilePhoneRepository.findById(id).get();
            mobilePhone.setModel(mobilePhoneDto.getModel());
            mobilePhone.setBrand(mobilePhoneDto.getBrand());
            mobilePhone.setNetworkConnectivity(mobilePhoneDto.getNetworkConnectivity());
            mobilePhone.setSimType(mobilePhoneDto.getSimType());
            mobilePhone.setDisplayType(mobilePhoneDto.getDisplayType());
            mobilePhone.setDisplayResolution(mobilePhoneDto.getDisplayResolution());
            mobilePhone.setDisplaySize(mobilePhoneDto.getDisplaySize());
            mobilePhone.setOs(mobilePhoneDto.getOs());
            mobilePhone.setBrandUi(mobilePhoneDto.getBrandUi());
            mobilePhone.setChipset(mobilePhoneDto.getChipset());
            mobilePhone.setCpuCore(mobilePhoneDto.getCpuCore());
            mobilePhone.setCpuClockSpeed(mobilePhoneDto.getCpuClockSpeed());
            mobilePhone.setGpu(mobilePhoneDto.getGpu());
            mobilePhone.setMemoryCordSlotSupported(mobilePhoneDto.isMemoryCordSlotSupported());
            mobilePhone.setInternalMemorySize(mobilePhoneDto.getInternalMemorySize());
            mobilePhone.setMainCameraCount(mobilePhoneDto.getMainCameraCount());
            mobilePhone.setMainCameraSpecs(mobilePhoneDto.getMainCameraSpecs());
            mobilePhone.setFrontCamera(mobilePhoneDto.isFrontCamera());
            mobilePhone.setFrontCameraSpecs(mobilePhoneDto.getFrontCameraSpecs());
            mobilePhone.setBatterySize(mobilePhoneDto.getBatterySize());
            mobilePhone.setChargingType(mobilePhoneDto.getChargingType());
            mobilePhone.setChargerOutput(mobilePhoneDto.getChargerOutput());
            mobilePhone.setColour(mobilePhoneDto.getColor());
            mobilePhone.setWeightInGrams(mobilePhoneDto.getWeightInGrams());
            mobilePhone.setPrice(mobilePhoneDto.getPrice());
            mobilePhone.setBluetooth(mobilePhoneDto.isBluetooth());
            mobilePhone.setGps(mobilePhoneDto.isGps());
            mobilePhone.setNfc(mobilePhoneDto.isNfc());
            mobilePhone.setRadio(mobilePhoneDto.isRadio());
            mobilePhone.setUsbType(mobilePhoneDto.getUsbType());
            mobilePhone.setAudioJack(mobilePhoneDto.isAudioJack());
            mobilePhone.setWlan(mobilePhoneDto.isWlan());
            mobilePhone.setAvailability(mobilePhoneDto.getAvailability());
            mobilePhone.setDiscountPercentage(mobilePhoneDto.getDiscountPercentage());
            mobilePhone.setWarranty(mobilePhoneDto.getWarranty());
            mobilePhone.setVendor(vendorRepository.findById(mobilePhoneDto.getVendorId()).get());
            mobilePhone.setActive(true);
            mobilePhoneRepository.save(mobilePhone);
            return mobilePhone;
        } else {
            throw new NotFound404Exception("Vendor not found");
        }
    }

    public MobilePhone getMobilePhoneById(Long id){
        return mobilePhoneRepository.findByMobilePhoneIdAndActiveTrue(id);
    }


    public String deactivateById(Long id){
        if (mobilePhoneRepository.findById(id).isPresent() && mobilePhoneRepository.findById(id).get().getActive()){
            MobilePhone mobilePhone =mobilePhoneRepository.findById(id).get();
            mobilePhone.setActive(false);
            mobilePhoneRepository.save(mobilePhone);
            return "Deactivate MobilePhone";
        }else {
            throw new NotFound404Exception("Mobile not found");
        }
    }

    public List<MobilePhone> getAllByModel(String model){
        return mobilePhoneRepository.findAllByModelLikeAndActiveTrue(model);
    }

    public List<MobilePhone> getAllByBrand(String brand){
        return mobilePhoneRepository.findAllByBrandLikeAndActiveTrue(brand);
    }

    public Map<String, Object> getFilteredMobilePhone(int page, int size, ElectronicFilterDto electronicFilterDto){
        Map<String, Object> map = new HashMap<>();
        if (size != 0){
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<MobilePhone> criteriaQuery = criteriaBuilder.createQuery(MobilePhone.class);
            Root<MobilePhone> acRoot = criteriaQuery.from(MobilePhone.class);
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
            List<MobilePhone> resources = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            map.put("result", resources);
            map.put("count", session.createQuery(criteriaQuery).getResultList().size());
            return map;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }

    public ElectronicFilterDto findAllDistinctData() {
        ElectronicFilterDto electronicFilterDto = new ElectronicFilterDto();
        electronicFilterDto.setModel(mobilePhoneRepository.findAllDistinctModel());
        electronicFilterDto.setBrand(mobilePhoneRepository.findAllDistinctBrand());
        electronicFilterDto.setPrice(mobilePhoneRepository.findAllDistinctPrice());
        electronicFilterDto.setColour(mobilePhoneRepository.findAllDistinctColour());
        electronicFilterDto.setAvailability(mobilePhoneRepository.findAllDistinctAvailability());
        return electronicFilterDto;
    }

    public List<MobilePhone> mobilePhoneSearchKeyword(String keyword, int page, int size) {
        if (size > 0) {
            Session session = entityManager.unwrap(Session.class);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<MobilePhone> criteriaQuery = criteriaBuilder.createQuery(MobilePhone.class);
            Root<MobilePhone> localStoreRoot = criteriaQuery.from(MobilePhone.class);
            Predicate predicateForData = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("model")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("brand")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("colour")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("availability")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("warranty")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("networkConnectivity")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("simType")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("displayType")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("displayResolution")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("displaySize")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("brandUi")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("chipset")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("cpuCore")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("os")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("gpu")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("cpuClockSpeed")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("internalMemorySize")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("mainCameraSpecs")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("frontCameraSpecs")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("batterySize")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("chargingType")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("chargerOutput")), "%" + keyword.toUpperCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.upper(localStoreRoot.get("usbType")), "%" + keyword.toUpperCase() + "%")
            );
            criteriaQuery.select(localStoreRoot).where(predicateForData).distinct(true);
            List<MobilePhone> resource = session.createQuery(criteriaQuery).setFirstResult(page * size).setMaxResults(size).getResultList();
            return resource;
        } else {
            throw new BadRequest400Exception("size can't be zero");
        }
    }
}
