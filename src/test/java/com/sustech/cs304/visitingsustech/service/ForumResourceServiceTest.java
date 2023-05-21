package com.sustech.cs304.visitingsustech.service;

import com.sustech.cs304.visitingsustech.VisitingSusTechApplication;
import com.sustech.cs304.visitingsustech.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VisitingSusTechApplication.class)
public class ForumResourceServiceTest {

    @Autowired
    private ForumResourceService forumResourceService;
    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;
    private ForumEntity forumEntity = new ForumEntity();
    private ForumResourceEntity forumResourceEntity = new ForumResourceEntity();

    @Test
    public void addTest(){
        userService.insertUser("3");
        forumEntity.setId(3);
        forumEntity.setOpenid("3");
        forumService.addForum(forumEntity);
        Assert.assertEquals(1, forumResourceService.addForumResource(3, "test"));
    }

    @Test
    public void getTest() {
        System.out.println(forumResourceService.getForumResource(3));
    }
}
