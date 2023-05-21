package com.sustech.cs304.visitingsustech.service;

import com.sustech.cs304.visitingsustech.VisitingSusTechApplication;
import com.sustech.cs304.visitingsustech.controller.CommentController;
import com.sustech.cs304.visitingsustech.entity.CommentEntity;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VisitingSusTechApplication.class)
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentController commentController;
    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;
    private ForumEntity forumEntity = new ForumEntity();
    private CommentEntity commentEntity = new CommentEntity();

    @Test
    public void addTest(){
        userService.insertUser("1");
        forumEntity.setId(1);
        forumEntity.setOpenid("1");
        forumService.addForum(forumEntity);
        commentEntity.setForumId(1);
        commentEntity.setNickname("eee");
        commentEntity.setOpenid("1");
        commentEntity.setContent("klj");
        Assert.assertEquals(1, commentService.addComment(commentEntity));
    }

    @Test
    public void getTest(){
        System.out.println(commentService.getComment(1));
    }

    @Test
    public void getNameTest(){
        System.out.println(commentService.getNameComment(1));
    }
}
