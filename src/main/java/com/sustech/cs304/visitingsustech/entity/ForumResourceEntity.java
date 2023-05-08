package com.sustech.cs304.visitingsustech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("forum_resource")
public class ForumResourceEntity {
    @TableId
    private Integer id;
    private Integer forumId;
    private String resource;
}
