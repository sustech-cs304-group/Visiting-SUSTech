package com.sustech.cs304.visitingsustech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Stores info of likes to a forum.
 *
 * @author pound
 */
@Data
@TableName("forum_like")
public class ForumLikeEntity {
    @TableId
    private Integer id;
    private String openid;
    private String nickname;
    private Integer forumId;
}
