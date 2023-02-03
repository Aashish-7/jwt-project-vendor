package com.jwtproject.products.controller;

import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.RefrigeratorDto;
import com.jwtproject.products.model.Refrigerator;
import com.jwtproject.products.service.RefrigeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/refrigerator")
public class RefrigeratorController {

    RefrigeratorService refrigeratorService;

    @Autowired
    public RefrigeratorController(RefrigeratorService refrigeratorService) {
        this.refrigeratorService = refrigeratorService;
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Refrigerator addRefrigerator(@RequestBody RefrigeratorDto refrigeratorDto){
        return refrigeratorService.addRefrigerator(refrigeratorDto);
    }

    @GetMapping("/{id}/getAllByStoreId")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public List<Refrigerator> getAllByVendorId(@PathVariable Long id) {
        return refrigeratorService.getAllByVendorId(id);
    }

    @PutMapping("/{id}/updateRefrigeratorById")
    public Refrigerator updateRefrigeratorById(@RequestBody RefrigeratorDto refrigeratorDto, @PathVariable Long id){
        return refrigeratorService.updateRefrigeratorById(refrigeratorDto, id);
    }

    @GetMapping("/{id}/getRefrigeratorById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Refrigerator getRefrigeratorById(@PathVariable Long id){
        return refrigeratorService.getRefrigeratorById(id);
    }

    @GetMapping("/{id}/deactivateById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id){
        return refrigeratorService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<Refrigerator> getAllByModel(@RequestParam String model){
        return refrigeratorService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<Refrigerator> getAllByBrand(@RequestParam String brand){
        return refrigeratorService.getAllByBrand(brand);
    }

    @GetMapping("/getFilteredRefrigerator")
    public Map<String, Object> getFilteredRefrigerator(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return refrigeratorService.getFilteredRefrigerator(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return refrigeratorService.findAllDistinctData();
    }
}
