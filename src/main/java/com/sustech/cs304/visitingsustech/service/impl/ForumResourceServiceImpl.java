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
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of forumResource service.
 *
 * @author pound
 */
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
        return forumResourceMapper.insert(forumResourceEntity);
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

    @Override
    public List<String> getFiles(Integer forumId) {
        ForumEntity forumEntity = forumMapper.selectById(forumId);
        if (forumEntity == null)
            throw new AppointmentException("Invalid forumID", 400);
        QueryWrapper<ForumResourceEntity> queryWrapper = new QueryWrapper<ForumResourceEntity>().eq("forum_id", forumId);
        List<String> files = new ArrayList<>();
        List<ForumResourceEntity> forumResourceEntities = forumResourceMapper.selectList(queryWrapper);
        for (ForumResourceEntity forumResourceEntity : forumResourceEntities) {
            files.add(forumResourceEntity.getResource());
        }
        return files;
    }
}
