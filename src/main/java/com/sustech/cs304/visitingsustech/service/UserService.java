package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;

public interface UserService extends IService<UserInfoEntity> {
    int insertUser(String openid);

    int updateUserInfo(String openid, String nickname, String avatarUrl);

    int updateUserInfo(String openid, UserInfoVo userInfoVo);

    UserInfoEntity queryUserInfo(String openid);
}
