package com.azki.service;

import com.azki.model.Companies;
import com.azki.repository.CompaniesDao;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesService {

    @Autowired
    CompaniesDao companiesDao;

    public List<Companies> getAllCompanies() {
        return companiesDao.findAll();
    }

    public List<Companies> getCompanies(List<Integer> ids) {
        return companiesDao.findByIDs(ids);
    }

    public RestResponse updateCompanies(Companies companies) {
        Companies returnCompanies = companiesDao.save(companies);

        if (returnCompanies.equals(companies)){
            return new RestResponse(true, "Companies Saved");
        } else {
            return new RestResponse(true, "Companies Updated");
        }
    }

    public RestResponse deleteCompanies(List<Integer> ids) {
        if (companiesDao.deleteByIDs(ids)){
            return new RestResponse(true, "Companies Deleted");
        } else {
            return new RestResponse(true, "Companies Not Deleted");
        }
    }
}
