package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.*;
import com.sustech.cs304.visitingsustech.exception.AppointmentException;
import com.sustech.cs304.visitingsustech.mapper.ForumLikeMapper;
import com.sustech.cs304.visitingsustech.mapper.ForumMapper;
import com.sustech.cs304.visitingsustech.mapper.ForumResourceMapper;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import com.sustech.cs304.visitingsustech.service.ForumLikeService;
import com.sustech.cs304.visitingsustech.service.ForumResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumLikeServiceImpl extends ServiceImpl<ForumLikeMapper, ForumLikeEntity> implements ForumLikeService {
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private ForumResourceMapper forumResourceMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ForumLikeMapper forumLikeMapper;

    @Override
    public int addForumLike(ForumLikeEntity forumLikeEntity) {
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(forumLikeEntity.getOpenid());
        if (userInfoEntity == null)
            throw new AppointmentException("Invalid userID", 400);
//        if (!IdCardValidator.isValid(appointmentEntity.getIdentityCard()))
//            throw new AppointmentException("Invalid identityCard", 400);
//        if (!appointmentEntity.getPhone().matches("^1[3-9]\\d{9}$"))
//            throw new AppointmentException("Invalid phone number", 400);
//        appointmentEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return forumLikeMapper.insert(forumLikeEntity);
    }

    @Override
    public int deleteForumLike(String openid, Integer id) {
        ForumLikeEntity forumLikeEntity = forumLikeMapper.selectById(id);
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(openid);
//        if (commentEntity == null || userInfoEntity == null)
//            throw new AppointmentException("Invalid forumID or userID", 400);
//        if (!(userInfoEntity.getType().equals("admin") || commentEntity.getOpenid().equals(openid)))
//            throw new AppointmentException("You are not allowed to delete this comment", 403);
        return forumLikeMapper.deleteById(id);
    }

    @Override
    public List<ForumLikeEntity> getForumLike(Integer forumId) {
        ForumEntity forumEntity = forumMapper.selectById(forumId);
        if (forumEntity == null)
            throw new AppointmentException("Invalid forumID", 400);
        QueryWrapper<ForumLikeEntity> forumLikeWrapper;
        forumLikeWrapper = new QueryWrapper<ForumLikeEntity>().eq("forum_id", forumId);
        return forumLikeMapper.selectList(forumLikeWrapper);
    }
}
