package com.sustech.cs304.visitingsustech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Stores comment info.
 *
 * @author pound
 */
@Data
@TableName("comment")
public class CommentEntity {
    @TableId
    private String openid;
    private String nickname;
    private Integer forumId;
    private String content;
}
