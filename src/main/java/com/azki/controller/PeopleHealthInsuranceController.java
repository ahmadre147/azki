package com.azki.controller;

import com.azki.model.PeopleHealthInsurance;
import com.azki.service.PeopleHealthInsuranceService;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeopleHealthInsuranceController {

    @Autowired
    PeopleHealthInsuranceService peopleHealthInsuranceService;

    @GetMapping("/api/getAllPeopleHealthInsurance")
    public RestResponse getAllPeopleHealthInsurance(){
        return new RestResponse(peopleHealthInsuranceService.getAllPeopleHealthInsurance());
    }

    @GetMapping("/api/getPeopleHealthInsurance")
    public RestResponse getPeopleHealthInsurance(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(peopleHealthInsuranceService.getPeopleHealthInsurance(ids));
    }

    @GetMapping("/api/deletePeopleHealthInsurance")
    public RestResponse deletePeopleHealthInsurance(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(peopleHealthInsuranceService.deletePeopleHealthInsurance(ids));
    }

    @PostMapping("/api/updateComment")
    public RestResponse updatePeopleHealthInsurance(PeopleHealthInsurance peopleHealthInsurance){
        return new RestResponse(peopleHealthInsuranceService.updatePeopleHealthInsurance(peopleHealthInsurance));
    }
}
