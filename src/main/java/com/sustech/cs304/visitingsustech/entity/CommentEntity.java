package com.sustech.cs304.visitingsustech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName("comment")
public class CommentEntity {
    @TableId
    private Integer id;
    private String openid;
    private Integer forumId;
    private String replyTo;
    private String content;
}
