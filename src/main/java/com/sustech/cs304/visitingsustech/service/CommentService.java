package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.*;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Comment services(add, query all comment info, query comment contents).
 *
 * @author pound
 */
public interface CommentService extends IService<CommentEntity> {
    public int addComment(CommentEntity commentEntity);

    public List<CommentEntity> getComment(Integer forumId);
    public Map<String, ArrayList<String>> getNameComment(Integer forumId);
}
