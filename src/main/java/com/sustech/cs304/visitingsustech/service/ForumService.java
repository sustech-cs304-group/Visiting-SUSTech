package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.AppointmentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;

import java.util.List;

/**
 * Forum services(add, query all forums, query forum of one user).
 *
 * @author pound
 */
public interface ForumService extends IService<ForumEntity> {
    public int addForum(ForumEntity ForumEntity);

    public List<ForumEntity> getForum();

    public List<ForumEntity> getForumById(String openid);
}
