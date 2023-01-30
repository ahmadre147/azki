package com.azki.service;

import com.azki.model.OTP;
import com.azki.repository.OTPDao;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OTPService {

    @Autowired
    OTPDao otpDao;

    public List<OTP> getAllOTP() {
        return otpDao.findAll();
    }

    public List<OTP> getOTP(List<Integer> ids) {
        return otpDao.findByIDs(ids);
    }

    public RestResponse updateOTP(OTP otp) {
        OTP returnOTP = otpDao.save(otp);

        if (returnOTP.equals(otp)){
            return new RestResponse(true, "OTP Saved");
        } else {
            return new RestResponse(true, "OTP Updated");
        }
    }

    public RestResponse deleteOTP(List<Integer> ids) {
        if (otpDao.deleteByIDs(ids)){
            return new RestResponse(true, "OTP Deleted");
        } else {
            return new RestResponse(true, "OTP Not Deleted");
        }
    }
}
