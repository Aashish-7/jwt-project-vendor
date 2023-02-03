package com.jwtproject.products.controller;

import com.jwtproject.products.dto.ACDto;
import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.model.AC;
import com.jwtproject.products.service.ACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/ac")
public class ACController {

    ACService acService;

    @Autowired
    public ACController(ACService acService) {
        this.acService = acService;
    }

//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    @PostMapping("/add")
    public AC addAc(@RequestBody ACDto acDto) {
        return acService.addAc(acDto);
    }

//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    @GetMapping("/{id}/getAllByVendorId")
    public List<AC> getAllByVendorId(@PathVariable Long id) {
        return acService.getAllByVendorId(id);
    }

//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    @PostMapping("/{id}/updateAcById")
    public AC updateAcById(@RequestBody ACDto acDto, @PathVariable Long id) {
        return acService.updateAcById(acDto, id);
    }

//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    @PutMapping("/{id}/updateAcByIdByPut")
    public AC updateAcByIdByPut(@RequestBody ACDto acDto, @PathVariable Long id) {
        return acService.updateAcByIdByPut(acDto, id);
    }

    @GetMapping("/{id}/getAcById")
    //@PreAuthorize("hasAnyAuthority('storeOwner')")
    public AC getAcById(@PathVariable Long id) {
        return acService.getAcById(id);
    }

    @GetMapping("/{id}/deactivateById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id) {
        return acService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<AC> getAllByModel(@RequestParam String model) {
        return acService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<AC> getAllByBrand(@RequestParam String brand) {
        return acService.getAllByBrand(brand);
    }

    @GetMapping("/getFilteredAc")
    public Map<String, Object> getFilteredAc(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return acService.getFilteredAc(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return acService.findAllDistinctData();
    }

    @GetMapping("/getAllAcByVendor")
    public List<AC> getAllAcByVendor(){
        return acService.getAllACByVendor();
    }
}
