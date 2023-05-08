package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import com.sustech.cs304.visitingsustech.entity.ForumResourceEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;

import java.util.List;

public interface ForumResourceService extends IService<ForumResourceEntity> {
    public int addForumResource(Integer forumId, String url);

    public int deleteForumResource(String openid, Integer id);

    public List<ForumResourceEntity> getForumResource(Integer forumId);
}
