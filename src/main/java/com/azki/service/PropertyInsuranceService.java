package com.azki.service;

import com.azki.model.PropertyInsurance;
import com.azki.repository.PropertyInsuranceDao;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyInsuranceService {

    @Autowired
    PropertyInsuranceDao propertyInsuranceDao;

    public List<PropertyInsurance> getAllPropertyInsurance() {
        return propertyInsuranceDao.findAll();
    }

    public List<PropertyInsurance> getPropertyInsurance(List<Integer> ids) {
        return propertyInsuranceDao.findByIDs(ids);
    }

    public RestResponse updatePropertyInsurance(PropertyInsurance propertyInsurance) {
        PropertyInsurance returnPropertyInsurance = propertyInsuranceDao.save(propertyInsurance);

        if (returnPropertyInsurance.equals(propertyInsurance)){
            return new RestResponse(true, "PropertyInsurance Saved");
        } else {
            return new RestResponse(true, "PropertyInsurance Updated");
        }
    }

    public RestResponse deletePropertyInsurance(List<Integer> ids) {
        if (propertyInsuranceDao.deleteByIDs(ids)){
            return new RestResponse(true, "PropertyInsurance Deleted");
        } else {
            return new RestResponse(true, "PropertyInsurance Not Deleted");
        }
    }
}
