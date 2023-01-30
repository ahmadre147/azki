package com.azki.service;

import com.azki.model.LifeInsurance;
import com.azki.repository.LifeInsuranceDao;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LifeInsuranceService {

    @Autowired
    LifeInsuranceDao lifeInsuranceDao;

    public List<LifeInsurance> getAllLifeInsurance() {
        return lifeInsuranceDao.findAll();
    }

    public List<LifeInsurance> getLifeInsurance(List<Integer> ids) {
        return lifeInsuranceDao.findByIDs(ids);
    }

    public RestResponse updateLifeInsurance(LifeInsurance lifeInsurance) {
        LifeInsurance returnLifeInsurance = lifeInsuranceDao.save(lifeInsurance);

        if (returnLifeInsurance.equals(lifeInsurance)){
            return new RestResponse(true, "LifeInsurance Saved");
        } else {
            return new RestResponse(true, "LifeInsurance Updated");
        }
    }

    public RestResponse deleteLifeInsurance(List<Integer> ids) {
        if (lifeInsuranceDao.deleteByIDs(ids)){
            return new RestResponse(true, "LifeInsurance Deleted");
        } else {
            return new RestResponse(true, "LifeInsurance Not Deleted");
        }
    }
}
