package com.hsrg.service.impl;

import com.github.pagehelper.PageHelper;
import com.hsrg.mapper.CommentMapper;
import com.hsrg.pojo.Comment;
import com.hsrg.service.CommentService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wiki.xsx.core.snowflake.config.Snowflake;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private Snowflake snowflake;

    @Override
    public Long createComment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        comment.setCommentId(snowflake.nextId());
        if(comment.getType() == 2){
            comment.setReplyTo(commentMapper.selectAuthorId(comment.getParentId()));
        }
        commentMapper.createComment(comment);
        return comment.getCommentId();
    }

    @Override
    public void deleteComment(Comment comment) {
        commentMapper.deleteComment(comment.getCommentId());
    }

    @Override
    public List<Comment> listCommentPageByParent(Comment comment, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber ,pageSize);

        List<Comment> list = commentMapper.listCommentByParent(comment);
        return list;
    }

    @Override
    public List<Comment> listCommentByParent(Comment comment) {
        List<Comment> list = commentMapper.listCommentByParent(comment);
        return list;
    }

    @Override
    public void listAllCommentByParent(Comment comment, List<Comment> l) {
        List<Comment> list = commentMapper.listCommentByParent(comment);
        if (list.size() != 0){
            for(Comment c : list){
                Comment target = new Comment();
                target.setParentId(c.getCommentId());
                target.setType(2);
                l.add(c);
                listAllCommentByParent(target, l);
            }
        }

    }

    @Override
    public Integer getAllCommentNum() {
        return commentMapper.selectCountComment();
    }
}
