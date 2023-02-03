package com.jwtproject.products.controller;

import com.jwtproject.products.dto.ElectronicFilterDto;
import com.jwtproject.products.dto.WashingMachineDto;
import com.jwtproject.products.model.WashingMachine;
import com.jwtproject.products.service.WashingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products/washingMachine")
public class WashingMachineController {

    WashingMachineService washingMachineService;

    @Autowired
    public WashingMachineController(WashingMachineService washingMachineService) {
        this.washingMachineService = washingMachineService;
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public WashingMachine addWashingMachine(@RequestBody WashingMachineDto washingMachineDto){
        return washingMachineService.addWashingMachine(washingMachineDto);
    }

    @GetMapping("/{id}/getAllByStoreId")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public List<WashingMachine> getAllByVendorId(@PathVariable Long id) {
        return washingMachineService.getAllByVendorId(id);
    }

    @PutMapping("/{id}/updateWashingMachineById")
//    @PreAuthorize("hasAnyAuthority('storeOwner', 'admin')")
    public WashingMachine updateWashingMachineById(@RequestBody WashingMachineDto washingMachineDto, @PathVariable Long id){
        return washingMachineService.updateWashingMachineById(washingMachineDto, id);
    }

    @GetMapping("/{id}/getWashingMachineById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public WashingMachine getWashingMachineById(@PathVariable Long id){
        return washingMachineService.getWashingMachineById(id);
    }

    @GetMapping("{id}/deactivateById")
//    @PreAuthorize("hasAnyAuthority('storeOwner')")
    public String deactivateById(@PathVariable Long id){
        return washingMachineService.deactivateById(id);
    }

    @GetMapping("/getAllByModel")
    public List<WashingMachine> getAllByModel(@RequestParam String model){
        return washingMachineService.getAllByModel(model);
    }

    @GetMapping("/getAllByBrand")
    public List<WashingMachine> getAllByBrand(@RequestParam String brand){
        return washingMachineService.getAllByBrand(brand);
    }

    @GetMapping("/getFilteredWashingMachine")
    public Map<String, Object> getFilteredWashingMachine(@RequestParam int page, @RequestParam int size, @RequestBody ElectronicFilterDto electronicFilterDto){
        return washingMachineService.getFilteredWashingMachine(page, size, electronicFilterDto);
    }

    @GetMapping("/findAllDistinctData")
    public ElectronicFilterDto findAllDistinctData(){
        return washingMachineService.findAllDistinctData();
    }
}
