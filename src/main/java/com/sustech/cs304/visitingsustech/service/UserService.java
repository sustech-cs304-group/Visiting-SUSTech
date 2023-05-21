package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;

/**
 * User services(add, update, query, add avatar).
 *
 * @author sui_h
 */
public interface UserService extends IService<UserInfoEntity> {
    int insertUser(String openid);

    int updateUserInfo(String openid, UserInfoVo userInfoVo);

    UserInfoEntity queryUserInfo(String openid);

    int updateAvatar(String openid, String url);
}
