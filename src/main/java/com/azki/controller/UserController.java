package com.azki.controller;

import com.azki.model.User;
import com.azki.service.UserService;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/api/getAllUser")
    public RestResponse getAllUser(){
        return new RestResponse(userService.getAllUser());
    }

    @GetMapping("/api/getUser")
    public RestResponse getUser(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(userService.getUser(ids));
    }

    @GetMapping("/api/deleteUser")
    public RestResponse deleteUser(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(userService.deleteUser(ids));
    }

    @PostMapping("/api/updateComment")
    public RestResponse updateUser(User user){
        return new RestResponse(userService.updateUser(user));
    }
}
