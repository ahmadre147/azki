package com.azki.service;

import com.azki.model.PeopleHealthInsurance;
import com.azki.repository.PeopleHealthInsuranceDao;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleHealthInsuranceService {

    @Autowired
    PeopleHealthInsuranceDao peopleHealthInsuranceDao;

    public List<PeopleHealthInsurance> getAllPeopleHealthInsurance() {
        return peopleHealthInsuranceDao.findAll();
    }

    public List<PeopleHealthInsurance> getPeopleHealthInsurance(List<Integer> ids) {
        return peopleHealthInsuranceDao.findByIDs(ids);
    }

    public RestResponse updatePeopleHealthInsurance(PeopleHealthInsurance peopleHealthInsurance) {
        PeopleHealthInsurance returnPeopleHealthInsurance = peopleHealthInsuranceDao.save(peopleHealthInsurance);

        if (returnPeopleHealthInsurance.equals(peopleHealthInsurance)){
            return new RestResponse(true, "PeopleHealthInsurance Saved");
        } else {
            return new RestResponse(true, "PeopleHealthInsurance Updated");
        }
    }

    public RestResponse deletePeopleHealthInsurance(List<Integer> ids) {
        if (peopleHealthInsuranceDao.deleteByIDs(ids)){
            return new RestResponse(true, "PeopleHealthInsurance Deleted");
        } else {
            return new RestResponse(true, "PeopleHealthInsurance Not Deleted");
        }
    }
}
