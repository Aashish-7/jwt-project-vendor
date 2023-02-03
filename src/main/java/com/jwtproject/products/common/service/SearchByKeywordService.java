package com.jwtproject.products.common.service;

import com.jwtproject.auth.service.VendorServiceImpl;
import com.jwtproject.products.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchByKeywordService {

    VendorServiceImpl vendorService;

    ACService acService;

    LaptopService laptopService;

    MobilePhoneService mobilePhoneService;

    RefrigeratorService refrigeratorService;

    TelevisionService televisionService;

    WashingMachineService washingMachineService;

    @Autowired
    public SearchByKeywordService(VendorServiceImpl vendorService, ACService acService, LaptopService laptopService, MobilePhoneService mobilePhoneService, RefrigeratorService refrigeratorService, TelevisionService televisionService, WashingMachineService washingMachineService) {
        this.vendorService = vendorService;
        this.acService = acService;
        this.laptopService = laptopService;
        this.mobilePhoneService = mobilePhoneService;
        this.refrigeratorService = refrigeratorService;
        this.televisionService = televisionService;
        this.washingMachineService = washingMachineService;
    }


    public Map<String, Object> searchKeyword(String keyword, int page, int size){
        Map<String, Object> map = new HashMap<>();
        map.put("localStore", vendorService.vendorSearchKeyword(keyword, page, size));
        map.put("ac", acService.acSearchKeyword(keyword,page, size));
        map.put("laptop", laptopService.laptopSearchKeyword(keyword, page, size));
        map.put("mobilePhone", mobilePhoneService.mobilePhoneSearchKeyword(keyword, page, size));
        map.put("refrigerator", refrigeratorService.refrigeratorSearchKeyword(keyword, page, size));
        map.put("television",  televisionService.televisionSearchKeyword(keyword, page, size));
        map.put("washingMachine", washingMachineService.washingMachineSearchKeyword(keyword, page, size));
        return map;
    }
}
