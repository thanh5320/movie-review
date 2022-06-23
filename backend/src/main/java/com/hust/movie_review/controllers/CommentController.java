package com.hust.movie_review.controllers;

import com.hust.movie_review.data.request.comment.StoreRequest;
import com.hust.movie_review.data.response.DfResponse;
import com.hust.movie_review.models.Comment;
import com.hust.movie_review.service.template.ICommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    ICommentService commentService;

    public CommentController(ICommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("store")
    public DfResponse<Comment> store(@RequestBody @Valid StoreRequest request){
        return DfResponse.okEntity(commentService.insert(request));
    }
}
