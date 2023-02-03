package com.jwtproject.products.controller;

import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.MobilePhoneDto;
import com.jwtproject.products.model.MobilePhone;
import com.jwtproject.products.service.MobilePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/mobilePhone")
public class MobilePhoneController {

    MobilePhoneService mobilePhoneService;

    @Autowired
    public MobilePhoneController(MobilePhoneService mobilePhoneService) {
        this.mobilePhoneService = mobilePhoneService;
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public MobilePhone addMobilePhone(@RequestBody MobilePhoneDto mobilePhoneDto){
        return mobilePhoneService.addMobilePhone(mobilePhoneDto);
    }

    @GetMapping("/{id}/getAllByStoreId")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public List<MobilePhone> getAllByVendorId(@PathVariable Long id) {
        return mobilePhoneService.getAllByVendorId(id);
    }

    @PutMapping("/{id}/updateMobilePhoneById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public MobilePhone updateMobilePhoneById(@RequestBody MobilePhoneDto mobilePhoneDto, @PathVariable Long id){
        return mobilePhoneService.updateMobilePhoneById(mobilePhoneDto, id);
    }

    @GetMapping("/{id}/getMobilePhoneById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public MobilePhone getMobilePhoneById(@PathVariable Long id){
        return mobilePhoneService.getMobilePhoneById(id);
    }

    @GetMapping("/{id}/deactivateById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id){
        return mobilePhoneService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<MobilePhone> getAllByModel(@RequestParam String model){
        return mobilePhoneService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<MobilePhone> getAllByBrand(@RequestParam String brand){
        return mobilePhoneService.getAllByBrand(brand);
    }

    @GetMapping("/getFilteredMobilePhone")
    public Map<String, Object> getFilteredMobilePhone(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return mobilePhoneService.getFilteredMobilePhone(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return mobilePhoneService.findAllDistinctData();
    }

}
