package com.hsrg.service;

import com.hsrg.pojo.Comment;

import java.util.List;

public interface CommentService {
    public Long createComment(Comment comment);

    void deleteComment(Comment comment);

    List<Comment> listCommentPageByParent(Comment comment, Integer pageNumber, Integer pageSize);

    List<Comment> listCommentByParent(Comment comment);
}
