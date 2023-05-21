package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sustech.cs304.visitingsustech.VisitingSusTechApplication;
import com.sustech.cs304.visitingsustech.entity.CommentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.mapper.UserInfoMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VisitingSusTechApplication.class)
public class ForumServiceTest {
    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    private ForumEntity forumEntity = new ForumEntity();

    @Test
    public void addTest(){
        userService.insertUser("99");
        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<UserInfoEntity>().eq("openid", "99");
        UserInfoEntity userInfoEntity = userInfoMapper.selectOne(queryWrapper);
        userInfoEntity.setType("admin");
        userInfoMapper.updateById(userInfoEntity);
        forumEntity.setId(99);
        forumEntity.setLocation("home");
        forumEntity.setCreateDate(Timestamp.valueOf("2002-10-10 00:00:00"));
        forumEntity.setOpenid("99");
        forumEntity.setContent("test");
        Assert.assertEquals(1, forumService.addForum(forumEntity));
    }

    @Test
    public void getTest(){
        System.out.println(forumService.getForum());
    }

    @Test
    public void getNameTest(){
        System.out.println(forumService.getForumById("99"));
    }
}
