package com.azki.controller;

import com.azki.model.Companies;
import com.azki.service.CompaniesService;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompaniesController {

    @Autowired
    CompaniesService companiesService;

    @GetMapping("/api/getAllCompanies")
    public RestResponse getAllCompanies(){
        return new RestResponse(companiesService.getAllCompanies());
    }

    @GetMapping("/api/getCompanies")
    public RestResponse getCompanies(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(companiesService.getCompanies(ids));
    }

    @GetMapping("/api/deleteCompanies")
    public RestResponse deleteCompanies(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(companiesService.deleteCompanies(ids));
    }

    @PostMapping("/api/updateComment")
    public RestResponse updateCompanies(Companies companies){
        return new RestResponse(companiesService.updateCompanies(companies));
    }
}
