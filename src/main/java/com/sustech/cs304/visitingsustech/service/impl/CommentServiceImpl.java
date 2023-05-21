package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.*;
import com.sustech.cs304.visitingsustech.exception.AppointmentException;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import com.sustech.cs304.visitingsustech.mapper.*;
import com.sustech.cs304.visitingsustech.service.*;
import com.sustech.cs304.visitingsustech.util.IdCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of comment service.
 *
 * @author pound
 */
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
            throw new BaseException("Invalid userID", 400);
        return commentMapper.insert(commentEntity);
    }

    @Override
    public List<CommentEntity> getComment(Integer forumId) {
        ForumEntity forumEntity = forumMapper.selectById(forumId);
        if (forumEntity == null)
            throw new BaseException("Invalid forumID", 400);
        QueryWrapper<CommentEntity> commentWrapper;
        commentWrapper = new QueryWrapper<CommentEntity>().eq("forum_id", forumId);
        return commentMapper.selectList(commentWrapper);
    }

    @Override
    public Map<String, ArrayList<String>> getNameComment(Integer forumId) {
        ForumEntity forumEntity = forumMapper.selectById(forumId);
        if (forumEntity == null)
            throw new AppointmentException("Invalid forumID", 400);
        QueryWrapper<CommentEntity> commentWrapper;
        commentWrapper = new QueryWrapper<CommentEntity>().eq("forum_id", forumId);
        Map<String, ArrayList<String>> map = new HashMap<>();
        List<CommentEntity> commentEntities = commentMapper.selectList(commentWrapper);
        for (CommentEntity commentEntity : commentEntities) {
            if (!map.containsKey(commentEntity.getNickname())) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(commentEntity.getContent());
                map.put(commentEntity.getNickname(), arrayList);
            }
            else {
                map.get(commentEntity.getNickname()).add(commentEntity.getContent());
            }
        }
        return map;
    }
}
