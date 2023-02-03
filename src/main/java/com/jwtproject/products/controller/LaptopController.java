package com.jwtproject.products.controller;

import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.LaptopDto;
import com.jwtproject.products.model.Laptop;
import com.jwtproject.products.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/laptop")
public class LaptopController {

    LaptopService laptopService;

    @Autowired
    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Laptop addLaptop(@RequestBody LaptopDto laptopDto){
        return laptopService.addLaptop(laptopDto);
    }

    @GetMapping("/{id}/getAllByVendorId")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public List<Laptop> getAllByVendorId(@PathVariable Long id) {
        return laptopService.getAllByVendorId(id);
    }

    @PutMapping("{id}/updateLaptopById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Laptop updateLaptopById(@RequestBody LaptopDto laptopDto, @PathVariable Long id){
        return laptopService.updateLaptopById(laptopDto, id);
    }

    @GetMapping("/{id}/getLaptopById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public Laptop getLaptopById(@PathVariable Long id){
        return laptopService.getLaptopById(id);
    }

    @GetMapping("/{id}/deactivateById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id){
        return laptopService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<Laptop> getAllByModel(@RequestParam String model){
        return laptopService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<Laptop> getAllByBrand(@RequestParam String brand){
        return laptopService.getAllByBrand(brand);
    }

    @GetMapping("/getFilteredLaptop")
    public Map<String, Object> getFilteredLaptop(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return laptopService.getFilteredLaptop(page, size, electronicFilterDto);
    }
    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return laptopService.findAllDistinctData();
    }
}
