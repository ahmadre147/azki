package com.azki.service;

import com.azki.model.HealthInsurance;
import com.azki.repository.HealthInsuranceDao;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthInsuranceService {

    @Autowired
    HealthInsuranceDao healthInsuranceDao;

    public List<HealthInsurance> getAllHealthInsurance() {
        return healthInsuranceDao.findAll();
    }

    public List<HealthInsurance> getHealthInsurance(List<Integer> ids) {
        return healthInsuranceDao.findByIDs(ids);
    }

    public RestResponse updateHealthInsurance(HealthInsurance healthInsurance) {
        HealthInsurance returnHealthInsurance = healthInsuranceDao.save(healthInsurance);

        if (returnHealthInsurance.equals(healthInsurance)){
            return new RestResponse(true, "HealthInsurance Saved");
        } else {
            return new RestResponse(true, "HealthInsurance Updated");
        }
    }

    public RestResponse deleteHealthInsurance(List<Integer> ids) {
        if (healthInsuranceDao.deleteByIDs(ids)){
            return new RestResponse(true, "HealthInsurance Deleted");
        } else {
            return new RestResponse(true, "HealthInsurance Not Deleted");
        }
    }
}
