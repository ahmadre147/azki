package com.azki.service;

import com.azki.model.User;
import com.azki.repository.UserDao;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getAllUser() {
        return userDao.findAll();
    }

    public List<User> getUser(List<Integer> ids) {
        return userDao.findByIDs(ids);
    }

    public RestResponse updateUser(User user) {
        User returnUser = userDao.save(user);

        if (returnUser.equals(user)){
            return new RestResponse(true, "User Saved");
        } else {
            return new RestResponse(true, "User Updated");
        }
    }

    public RestResponse deleteUser(List<Integer> ids) {
        if (userDao.deleteByIDs(ids)){
            return new RestResponse(true, "User Deleted");
        } else {
            return new RestResponse(true, "User Not Deleted");
        }
    }
}
