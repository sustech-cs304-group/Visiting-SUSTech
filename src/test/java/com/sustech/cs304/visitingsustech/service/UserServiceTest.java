package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sustech.cs304.visitingsustech.VisitingSusTechApplication;
import com.sustech.cs304.visitingsustech.entity.CommentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VisitingSusTechApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void addTest(){
        Assert.assertEquals(1, userService.insertUser("5"));
    }

    @Test
    public void updateTest(){
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setName("test");
        userInfoVo.setPhone("13021222222");
        userInfoVo.setGender(0);
        userInfoVo.setNickname("test");
        userInfoVo.setIdentityCard("370303200109137410");
        Assert.assertEquals(1, userService.updateUserInfo("5", userInfoVo));
    }

    @Test
    public void getTest(){
        System.out.println(userService.queryUserInfo("5"));
    }

    @Test
    public void updateAvatarTest(){
        Assert.assertEquals(0, userService.updateAvatar("5", "http"));
    }
}
