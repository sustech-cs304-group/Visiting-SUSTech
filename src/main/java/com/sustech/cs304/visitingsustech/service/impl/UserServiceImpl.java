package com.sustech.cs304.visitingsustech.service.impl;

import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import com.sustech.cs304.visitingsustech.service.UserService;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int insertUser(String openid) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setOpenid(openid);
        return userInfoMapper.insert(userInfoEntity);
    }

    @Override
    public int updateUserInfo(String openid, String nickname, String avatarUrl) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setNickname(nickname);
        userInfoEntity.setAvatarUrl(avatarUrl);
        return userInfoMapper.updateById(userInfoEntity);
    }

    @Override
    public int updateUserInfo(String openid, UserInfoVo userInfoVo) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        BeanUtils.copyProperties(userInfoVo, userInfoEntity);
        userInfoEntity.setOpenid(openid);
        return userInfoMapper.updateById(userInfoEntity);
    }

    @Override
    public UserInfoEntity queryUserInfo(String openid) {
        return userInfoMapper.selectById(openid);
    }
}
