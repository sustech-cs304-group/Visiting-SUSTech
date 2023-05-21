package com.sustech.cs304.visitingsustech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Stores basic forum info.
 *
 * @author pound
 */
@Data
@TableName("forum")
public class ForumEntity {
    @TableId
    private Integer id;
    private String openid;
    private String content;
    private String location;
    private Timestamp createDate;
}
