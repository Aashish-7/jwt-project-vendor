package com.jwtproject.products.controller;

import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.TelevisionDto;
import com.jwtproject.products.model.Television;
import com.jwtproject.products.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/television")
public class TelevisionController {

    TelevisionService televisionService;

    @Autowired
    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Television addTelevision(@RequestBody TelevisionDto televisionDto){
        return televisionService.addTelevision(televisionDto);
    }

    @GetMapping("/{id}/getAllByStoreId")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public List<Television> getAllByVendorId(@PathVariable Long id) {
        return televisionService.getAllByVendorId(id);
    }

    @PutMapping("/{id}/updateTelevisionById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Television updateTelevisionById(@RequestBody TelevisionDto televisionDto, @PathVariable Long id){
        return televisionService.updateTelevisionById(televisionDto, id);
    }

    @GetMapping("/{id}/getTelevisionById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Television getTelevisionById(@PathVariable Long id){
        return televisionService.getTelevisionById(id);
    }

    @GetMapping("/{id}/deactivateById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id){
        return televisionService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<Television> getAllByModel(@RequestParam String model){
        return televisionService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<Television> getAllByBrand(@RequestParam String brand){
        return televisionService.getAllByBrand(brand);
    }
    @GetMapping("/getFilteredTelevision")
    public Map<String, Object> getFilteredTelevision(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return televisionService.getFilteredTelevision(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return televisionService.findAllDistinctData();
    }
}
