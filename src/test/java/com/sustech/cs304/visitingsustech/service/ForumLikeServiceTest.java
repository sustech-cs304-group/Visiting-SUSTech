package com.sustech.cs304.visitingsustech.service;

import com.sustech.cs304.visitingsustech.VisitingSusTechApplication;
import com.sustech.cs304.visitingsustech.entity.CommentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import com.sustech.cs304.visitingsustech.entity.ForumLikeEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VisitingSusTechApplication.class)
public class ForumLikeServiceTest {

    @Autowired
    private ForumLikeService forumLikeService;
    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;
    private ForumEntity forumEntity = new ForumEntity();
    private ForumLikeEntity forumLikeEntity = new ForumLikeEntity();

    @Test
    public void addTest(){
        userService.insertUser("2");
        forumEntity.setOpenid("2");
        forumEntity.setId(2);
        forumService.addForum(forumEntity);
        forumLikeEntity.setForumId(2);
        forumLikeEntity.setNickname("eee");
        forumLikeEntity.setOpenid("2");
        Assert.assertEquals(1, forumLikeService.addForumLike(forumLikeEntity));
    }

    @Test
    public void getTest(){
        System.out.println(forumLikeService.getForumLike(2));
    }

    @Test
    public void getNameTest(){
        System.out.println(forumLikeService.getLikeNames(2));
    }
}
