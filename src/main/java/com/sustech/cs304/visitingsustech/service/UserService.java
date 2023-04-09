package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends IService<UserInfoEntity> {
    int insertUser(String openid);

    int updateUserInfo(String openid, UserInfoVo userInfoVo);

    UserInfoEntity queryUserInfo(String openid);

    int updateAvatar(String openid, MultipartFile avatar, String baseUrl);
}
