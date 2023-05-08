package com.sustech.cs304.visitingsustech.vo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class CommentVo {
    private String forumId;
    private String replyTo;
    private String content;
}
