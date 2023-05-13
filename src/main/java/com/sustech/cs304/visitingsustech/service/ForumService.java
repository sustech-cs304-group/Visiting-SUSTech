package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;

import java.util.List;

public interface ForumService extends IService<ForumEntity> {
    public int addForum(ForumEntity ForumEntity);

    public int deleteForum(String openid, Integer id);

    public List<ForumEntity> getForum();

    public List<ForumEntity> getForumById(String openid);
}
