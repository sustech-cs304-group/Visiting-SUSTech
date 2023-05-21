package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.*;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;

import java.util.List;

/**
 * ForumLike services(add, query all forumLike info, query usernames of likes).
 *
 * @author pound
 */
public interface ForumLikeService extends IService<ForumLikeEntity> {
    public int addForumLike(ForumLikeEntity forumLikeEntity);

    public List<ForumLikeEntity> getForumLike(Integer forumId);

    public List<String> getLikeNames(Integer forumId);
}
