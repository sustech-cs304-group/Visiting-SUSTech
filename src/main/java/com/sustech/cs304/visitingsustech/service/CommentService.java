package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.*;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;

import java.util.List;
import java.util.Map;

public interface CommentService extends IService<CommentEntity> {
    public int addComment(CommentEntity commentEntity);

    public int deleteComment(String openid, Integer id);

    public List<CommentEntity> getComment(Integer forumId);

    public Map<String, String> getNameComment(Integer forumId);
}
