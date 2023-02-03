package com.jwtproject.auth.controller;

import com.jwtproject.auth.dto.VendorDto;
import com.jwtproject.auth.service.VendorServiceImpl;
import com.jwtproject.products.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendorController {

    private VendorServiceImpl vendorService;

    @Autowired
    public VendorController(VendorServiceImpl vendorService) {
        this.vendorService = vendorService;
    }

    @RequestMapping(value = "/addVendor", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody VendorDto vendorDto) throws Exception {
        return ResponseEntity.ok(vendorService.addVendor(vendorDto));
    }

    @PutMapping("/updateVendor/{id}")
    public String updateVendor(@RequestBody VendorDto vendorDto, @PathVariable Long id){
        return vendorService.updateVendor(vendorDto, id);
    }

    @PostMapping("/changePassword/{id}")
    public String changePassword(@RequestBody VendorDto vendorDto, @PathVariable Long id) throws Exception {
        return vendorService.changePassword(vendorDto, id);
    }

    @GetMapping("/getAllAcByVendorId/{id}")
    public List<AC> getAllAcByVendorId(@PathVariable Long id){
        return vendorService.getAllAcByVendorId(id);
    }

    @GetMapping("/getAllLaptopByVendorId/{id}")
    public List<Laptop> getAllLaptopByVendorId(@PathVariable Long id){
        return vendorService.getAllLaptopByVendorId(id);
    }

    @GetMapping("/getAllMobilePhoneByVendorId/{id}")
    public List<MobilePhone> getAllMobilePhoneByVendorId(@PathVariable Long id){
        return vendorService.getAllMobilePhoneByVendorId(id);
    }

    @GetMapping("/getAllRefrigeratorByVendorId/{id}")
    public List<Refrigerator> getAllRefrigeratorByVendorId(@PathVariable Long id){
        return vendorService.getAllRefrigeratorByVendorId(id);
    }

    @GetMapping("/getAllTelevisionByVendorId/{id}")
    public List<Television> getAllTelevisionByVendorId(@PathVariable Long id){
        return vendorService.getAllTelevisionByVendorId(id);
    }

    @GetMapping("/getAllWashingMachineByVendorId")
    public List<WashingMachine> getAllWashingMachineByVendorId(@PathVariable Long id){
        return vendorService.getAllWashingMachineByVendorId(id);
    }
}
