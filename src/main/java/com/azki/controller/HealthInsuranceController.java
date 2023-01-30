package com.azki.controller;

import com.azki.model.HealthInsurance;
import com.azki.service.HealthInsuranceService;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HealthInsuranceController {

    @Autowired
    HealthInsuranceService healthInsuranceService;

    @GetMapping("/api/getAllHealthInsurance")
    public RestResponse getAllHealthInsurance(){
        return new RestResponse(healthInsuranceService.getAllHealthInsurance());
    }

    @GetMapping("/api/getHealthInsurance")
    public RestResponse getHealthInsurance(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(healthInsuranceService.getHealthInsurance(ids));
    }

    @GetMapping("/api/deleteHealthInsurance")
    public RestResponse deleteHealthInsurance(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(healthInsuranceService.deleteHealthInsurance(ids));
    }

    @PostMapping("/api/updateComment")
    public RestResponse updateHealthInsurance(HealthInsurance healthInsurance){
        return new RestResponse(healthInsuranceService.updateHealthInsurance(healthInsurance));
    }
}
