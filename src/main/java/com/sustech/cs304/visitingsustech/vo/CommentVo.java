package com.sustech.cs304.visitingsustech.vo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Vo for comment info introduction.
 *
 * @author pound
 */
@Data
public class CommentVo {
    private Integer forumId;
    private String content;
}
