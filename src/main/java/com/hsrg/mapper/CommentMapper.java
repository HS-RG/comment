package com.hsrg.mapper;

import com.hsrg.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(comment_id, parent_id, type, reply_to, author_id, context, update_time, create_time)" +
            "values (#{commentId}, #{parentId}, #{type}, #{replyTo}, #{authorId}, #{context}, #{updateTime}, #{createTime})")
    public void createComment(Comment comment);

    @Update("update comment set context = null where comment_id = #{commentId}")
    void deleteComment(Long commentId);

    @Select("select * from comment where type = #{type} and parent_id = #{parentId}")
    List<Comment> listCommentByParent(Comment comment);

    @Select("select author_id from comment where comment_id = #{commentId}")
    Long selectAuthorId(Long commentId);
}
