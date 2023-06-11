package com.hsrg.controller;

import com.hsrg.pojo.Result;
import com.hsrg.pojo.Comment;
import com.hsrg.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/createComment")
    private Result createComment(@RequestBody Comment comment){
        return Result.success(commentService.createComment(comment));
    }

    @PostMapping("/comment/deleteComment")
    private Result deleteComment(@RequestBody Comment comment){
        commentService.deleteComment(comment);
        return Result.success();
    }

    @PostMapping("/comment/listCommentPageByParent")
    private Result listCommentPageByParent(@RequestBody Comment comment, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return Result.success(commentService.listCommentPageByParent(comment, pageNumber, pageSize));
    }

    @PostMapping("/comment/listCommentByParent")
    private Result listCommentByParent(@RequestBody Comment comment){
        return Result.success(commentService.listCommentByParent(comment));
    }

    @PostMapping("/comment/listAllCommentByParent")
    private Result listAllCommentByParent(@RequestBody Comment comment){
        List<Comment> l =new ArrayList<Comment>();
        commentService.listAllCommentByParent(comment, l);
        return Result.success(l);
    }

    @PostMapping("/comment/getAllCommentNum")
    public Result getAllCommentNum(){
        return Result.success(commentService.getAllCommentNum());
    }

}
