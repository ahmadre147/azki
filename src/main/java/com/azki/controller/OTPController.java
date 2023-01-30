package com.azki.controller;

import com.azki.model.OTP;
import com.azki.service.OTPService;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OTPController {

    @Autowired
    OTPService otpService;

    @GetMapping("/api/getAllOTP")
    public RestResponse getAllOTP(){
        return new RestResponse(otpService.getAllOTP());
    }

    @GetMapping("/api/getOTP")
    public RestResponse getOTP(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(otpService.getOTP(ids));
    }

    @GetMapping("/api/deleteOTP")
    public RestResponse deleteOTP(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(otpService.deleteOTP(ids));
    }

    @PostMapping("/api/updateComment")
    public RestResponse updateOTP(OTP otp){
        return new RestResponse(otpService.updateOTP(otp));
    }
}
