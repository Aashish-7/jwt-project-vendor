package com.jwtproject.auth.controller;

import com.jwtproject.products.model.AC;
import com.jwtproject.products.service.ACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerAPIController {

    ACService acService;

    @Autowired
    public CustomerAPIController(ACService acService) {
        this.acService = acService;
    }

    @GetMapping("/getAllAcByCustomer")
    public List<AC> getAllAcByCustomer(){
        return acService.getAllACByVendor();
    }
}
