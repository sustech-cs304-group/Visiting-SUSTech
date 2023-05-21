package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.*;
import com.sustech.cs304.visitingsustech.exception.AppointmentException;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import com.sustech.cs304.visitingsustech.mapper.ForumLikeMapper;
import com.sustech.cs304.visitingsustech.mapper.ForumMapper;
import com.sustech.cs304.visitingsustech.mapper.ForumResourceMapper;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import com.sustech.cs304.visitingsustech.service.ForumLikeService;
import com.sustech.cs304.visitingsustech.service.ForumResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of forumLike service.
 *
 * @author pound
 */
@Service
public class ForumLikeServiceImpl extends ServiceImpl<ForumLikeMapper, ForumLikeEntity> implements ForumLikeService {
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ForumLikeMapper forumLikeMapper;

    @Override
    public int addForumLike(ForumLikeEntity forumLikeEntity) {
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(forumLikeEntity.getOpenid());
        if (userInfoEntity == null)
            throw new BaseException("Invalid userID", 400);
        return forumLikeMapper.insert(forumLikeEntity);
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

    @Override
    public List<String> getLikeNames(Integer forumId) {
        ForumEntity forumEntity = forumMapper.selectById(forumId);
        if (forumEntity == null)
            throw new AppointmentException("Invalid forumID", 400);
        QueryWrapper<ForumLikeEntity> forumLikeWrapper;
        forumLikeWrapper = new QueryWrapper<ForumLikeEntity>().eq("forum_id", forumId);
        List<ForumLikeEntity> forumLikeEntities = forumLikeMapper.selectList(forumLikeWrapper);
        List<String> names = new ArrayList<>();
        for (ForumLikeEntity forumLikeEntity : forumLikeEntities) {
            names.add(forumLikeEntity.getNickname());
        }
        return names;
    }
}
