package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.*;
import com.sustech.cs304.visitingsustech.exception.AppointmentException;
import com.sustech.cs304.visitingsustech.mapper.*;
import com.sustech.cs304.visitingsustech.service.*;
import com.sustech.cs304.visitingsustech.util.IdCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private ForumResourceMapper forumResourceMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int addComment(CommentEntity commentEntity) {
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(commentEntity.getOpenid());
        if (userInfoEntity == null)
            throw new AppointmentException("Invalid userID", 400);
//        if (!IdCardValidator.isValid(appointmentEntity.getIdentityCard()))
//            throw new AppointmentException("Invalid identityCard", 400);
//        if (!appointmentEntity.getPhone().matches("^1[3-9]\\d{9}$"))
//            throw new AppointmentException("Invalid phone number", 400);
//        appointmentEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return commentMapper.insert(commentEntity);
    }

    @Override
    public int deleteComment(String openid, Integer id) {
        CommentEntity commentEntity = commentMapper.selectById(id);
        UserInfoEntity userInfoEntity = userInfoMapper.selectById(openid);
//        if (commentEntity == null || userInfoEntity == null)
//            throw new AppointmentException("Invalid forumID or userID", 400);
//        if (!(userInfoEntity.getType().equals("admin") || commentEntity.getOpenid().equals(openid)))
//            throw new AppointmentException("You are not allowed to delete this comment", 403);
        return commentMapper.deleteById(id);
    }

    @Override
    public List<CommentEntity> getComment(Integer forumId) {
        ForumEntity forumEntity = forumMapper.selectById(forumId);
        if (forumEntity == null)
            throw new AppointmentException("Invalid forumID", 400);
        QueryWrapper<CommentEntity> commentWrapper;
        commentWrapper = new QueryWrapper<CommentEntity>().eq("forum_id", forumId);
        return commentMapper.selectList(commentWrapper);
    }

    @Override
    public Map<String, String> getNameComment(Integer forumId) {
        ForumEntity forumEntity = forumMapper.selectById(forumId);
        if (forumEntity == null)
            throw new AppointmentException("Invalid forumID", 400);
        QueryWrapper<CommentEntity> commentWrapper;
        commentWrapper = new QueryWrapper<CommentEntity>().eq("forum_id", forumId);
        Map<String, String> map = new HashMap<>();
        List<CommentEntity> commentEntities = commentMapper.selectList(commentWrapper);
        for (CommentEntity commentEntity : commentEntities) {
            map.put(commentEntity.getNickname(), commentEntity.getContent());
        }
        return map;
    }
}
