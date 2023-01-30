package com.azki.controller;

import com.azki.model.LifeInsurance;
import com.azki.service.LifeInsuranceService;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LifeInsuranceController {

    @Autowired
    LifeInsuranceService lifeInsuranceService;

    @GetMapping("/api/getAllLifeInsurance")
    public RestResponse getAllLifeInsurance(){
        return new RestResponse(lifeInsuranceService.getAllLifeInsurance());
    }

    @GetMapping("/api/getLifeInsurance")
    public RestResponse getLifeInsurance(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(lifeInsuranceService.getLifeInsurance(ids));
    }

    @GetMapping("/api/deleteLifeInsurance")
    public RestResponse deleteLifeInsurance(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(lifeInsuranceService.deleteLifeInsurance(ids));
    }

    @PostMapping("/api/updateComment")
    public RestResponse updateLifeInsurance(LifeInsurance lifeInsurance){
        return new RestResponse(lifeInsuranceService.updateLifeInsurance(lifeInsurance));
    }
}
