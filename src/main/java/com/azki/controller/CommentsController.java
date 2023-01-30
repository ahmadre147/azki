package com.azki.controller;

import com.azki.model.Comments;
import com.azki.service.CommentsService;
import com.azki.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    CommentsService commentsService;

    @GetMapping("/api/getAllComments")
    public RestResponse getAllComments(){
        return new RestResponse(commentsService.getAllComments());
    }

    @GetMapping("/api/getComments")
    public RestResponse getComments(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(commentsService.getComments(ids));
    }

    @GetMapping("/api/deleteComments")
    public RestResponse deleteComments(@RequestParam(value="ids") List<Integer> ids){
        return new RestResponse(commentsService.deleteComments(ids));
    }

    @PostMapping("/api/updateComment")
    public RestResponse updateComments(Comments comments){
        return new RestResponse(commentsService.updateComments(comments));
    }
}
