package com.azki.service;

import com.azki.model.Reminder;
import com.azki.repository.ReminderDao;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    @Autowired
    ReminderDao reminderDao;

    public List<Reminder> getAllReminder() {
        return reminderDao.findAll();
    }

    public List<Reminder> getReminder(List<Integer> ids) {
        return reminderDao.findByIDs(ids);
    }

    public RestResponse updateReminder(Reminder reminder) {
        Reminder returnReminder = reminderDao.save(reminder);

        if (returnReminder.equals(reminder)){
            return new RestResponse(true, "Reminder Saved");
        } else {
            return new RestResponse(true, "Reminder Updated");
        }
    }

    public RestResponse deleteReminder(List<Integer> ids) {
        if (reminderDao.deleteByIDs(ids)){
            return new RestResponse(true, "Reminder Deleted");
        } else {
            return new RestResponse(true, "Reminder Not Deleted");
        }
    }
}
