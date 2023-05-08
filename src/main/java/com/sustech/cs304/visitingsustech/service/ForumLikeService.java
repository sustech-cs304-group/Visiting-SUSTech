package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.*;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;

import java.util.List;

public interface ForumLikeService extends IService<ForumLikeEntity> {
    public int addForumLike(ForumLikeEntity forumLikeEntity);

    public int deleteForumLike(String openid, Integer id);

    public List<ForumLikeEntity> getForumLike(Integer forumId);
}
