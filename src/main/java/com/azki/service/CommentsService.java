package com.azki.service;

import com.azki.model.Comments;
import com.azki.repository.CommentsDao;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    CommentsDao commentsDao;

    public List<Comments> getAllComments() {
        return commentsDao.findAll();
    }

    public List<Comments> getComments(List<Integer> ids) {
        return commentsDao.findByIDs(ids);
    }

    public RestResponse updateComments(Comments comments) {
        Comments returnComments = commentsDao.save(comments);

        if (returnComments.equals(comments)){
            return new RestResponse(true, "Comments Saved");
        } else {
            return new RestResponse(true, "Comments Updated");
        }
    }

    public RestResponse deleteComments(List<Integer> ids) {
        if (commentsDao.deleteByIDs(ids)){
            return new RestResponse(true, "Comments Deleted");
        } else {
            return new RestResponse(true, "Comments Not Deleted");
        }
    }
}
