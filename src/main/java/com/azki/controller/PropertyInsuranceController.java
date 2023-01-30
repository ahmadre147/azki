package com.azki.controller;

import com.azki.model.PropertyInsurance;
import com.azki.service.PropertyInsuranceService;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PropertyInsuranceController {

    @Autowired
    PropertyInsuranceService propertyInsuranceService;

    @GetMapping("/api/getAllPropertyInsurance")
    public RestResponse getAllPropertyInsurance(){
        return new RestResponse(propertyInsuranceService.getAllPropertyInsurance());
    }

    @GetMapping("/api/getPropertyInsurance")
    public RestResponse getPropertyInsurance(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(propertyInsuranceService.getPropertyInsurance(ids));
    }

    @GetMapping("/api/deletePropertyInsurance")
    public RestResponse deletePropertyInsurance(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(propertyInsuranceService.deletePropertyInsurance(ids));
    }

    @PostMapping("/api/updateComment")
    public RestResponse updatePropertyInsurance(PropertyInsurance propertyInsurance){
        return new RestResponse(propertyInsuranceService.updatePropertyInsurance(propertyInsurance));
    }
}
