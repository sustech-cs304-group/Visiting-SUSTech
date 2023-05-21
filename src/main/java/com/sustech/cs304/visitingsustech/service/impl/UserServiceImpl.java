package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.exception.UpdateUserInfoException;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import com.sustech.cs304.visitingsustech.service.UserService;
import com.sustech.cs304.visitingsustech.util.IdCardValidator;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * Implementation of user service.
 *
 * @author pound
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Value("${spring.servlet.multipart.location}")
    private String path;

    @Override
    public int insertUser(String openid) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setOpenid(openid);
        return userInfoMapper.insert(userInfoEntity);
    }

    @Override
    public int updateUserInfo(String openid, UserInfoVo userInfoVo) {
        if (!IdCardValidator.isValid(userInfoVo.getIdentityCard()))
            throw new UpdateUserInfoException("Invalid ID card");
        if (!userInfoVo.getPhone().matches("^1[3-9]\\d{9}$"))
            throw new UpdateUserInfoException("Invalid phone number");
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        BeanUtils.copyProperties(userInfoVo, userInfoEntity);
        userInfoEntity.setOpenid(openid);
        userInfoEntity.setType("user");
        return userInfoMapper.updateById(userInfoEntity);
    }

    @Override
    public UserInfoEntity queryUserInfo(String openid) {
        return userInfoMapper.selectById(openid);
    }

    @Override
    public int updateAvatar(String openid, String url) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setOpenid(openid);
        userInfoEntity.setAvatarUrl(url);
        return userInfoMapper.updateById(userInfoEntity);
    }
}