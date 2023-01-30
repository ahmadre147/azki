package com.azki.controller;

import com.azki.model.Reminder;
import com.azki.service.ReminderService;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReminderController {

    @Autowired
    ReminderService reminderService;

    @GetMapping("/api/getAllReminder")
    public RestResponse getAllReminder(){
        return new RestResponse(reminderService.getAllReminder());
    }

    @GetMapping("/api/getReminder")
    public RestResponse getReminder(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(reminderService.getReminder(ids));
    }

    @GetMapping("/api/deleteReminder")
    public RestResponse deleteReminder(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(reminderService.deleteReminder(ids));
    }

    @PostMapping("/api/updateComment")
    public RestResponse updateReminder(Reminder reminder){
        return new RestResponse(reminderService.updateReminder(reminder));
    }
}
