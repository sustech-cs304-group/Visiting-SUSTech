package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import com.sustech.cs304.visitingsustech.entity.ForumResourceEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.exception.AppointmentException;
import com.sustech.cs304.visitingsustech.mapper.AppointmentMapper;
import com.sustech.cs304.visitingsustech.mapper.ForumMapper;
import com.sustech.cs304.visitingsustech.mapper.ForumResourceMapper;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import com.sustech.cs304.visitingsustech.service.AppointmentService;
import com.sustech.cs304.visitingsustech.service.ForumResourceService;
import com.sustech.cs304.visitingsustech.service.ForumService;
import com.sustech.cs304.visitingsustech.util.IdCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ForumResourceServiceImpl extends ServiceImpl<ForumResourceMapper, ForumResourceEntity> implements ForumResourceService {
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private ForumResourceMapper forumResourceMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int addForumResource(Integer forumId, String url) {
        ForumResourceEntity forumResourceEntity = new ForumResourceEntity();
        forumResourceEntity.setResource(url);
        forumResourceEntity.setForumId(forumId);
//        if (userInfoEntity == null)
//            throw new AppointmentException("Invalid userID", 400);
//        if (!IdCardValidator.isValid(appointmentEntity.getIdentityCard()))
//            throw new AppointmentException("Invalid identityCard", 400);
//        if (!appointmentEntity.getPhone().matches("^1[3-9]\\d{9}$"))
//            throw new AppointmentException("Invalid phone number", 400);
//        appointmentEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return forumResourceMapper.insert(forumResourceEntity);
    }

    @Override
    public int deleteForumResource(String openid, Integer id) {
        ForumEntity forumEntity = forumMapper.selectById(id);
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(openid);
//        if (forumEntity == null || userInfoEntity == null)
//            throw new AppointmentException("Invalid forumID or userID", 400);
//        if (!(userInfoEntity.getType().equals("admin") || forumEntity.getOpenid().equals(openid)))
//            throw new AppointmentException("You are not allowed to delete this appointment", 403);
        return forumResourceMapper.deleteById(id);
    }

    @Override
    public List<ForumResourceEntity> getForumResource(Integer forumId) {
        ForumEntity forumEntity = forumMapper.selectById(forumId);
        if (forumEntity == null)
            throw new AppointmentException("Invalid forumID", 400);
        QueryWrapper<ForumResourceEntity> forumResourceWrapper;
        forumResourceWrapper = new QueryWrapper<ForumResourceEntity>().eq("forum_id", forumId);
        return forumResourceMapper.selectList(forumResourceWrapper);
    }
}
