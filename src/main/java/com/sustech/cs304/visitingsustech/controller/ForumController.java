package com.sustech.cs304.visitingsustech.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.*;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import com.sustech.cs304.visitingsustech.mapper.AppointmentMapper;
import com.sustech.cs304.visitingsustech.mapper.ForumMapper;
import com.sustech.cs304.visitingsustech.service.*;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.vo.AppointmentVo;
import com.sustech.cs304.visitingsustech.vo.ForumVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * For forum operations.
 *
 * @author pound
 */
@RestController
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private ForumResourceService forumResourceService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ForumLikeService forumLikeService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Value("${spring.servlet.multipart.location}")
    private String path;

    /**
     * Add a forum.
     *
     * @param forumVo Forum info to add
     * @param request Http request
     * @return Message of success or fail
     */
    @PostMapping("/add")
    public JsonResult<String> addForum(@RequestBody ForumVo forumVo,
                                             HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        ForumEntity forumEntity = new ForumEntity();
        forumEntity.setOpenid(openid);
        forumEntity.setLocation(forumVo.getLocation());
        forumEntity.setContent(forumVo.getContent());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        forumEntity.setCreateDate(timestamp);
        try {
            if (forumService.addForum(forumEntity) > 0) {
                QueryWrapper<ForumEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("openid", openid).eq("create_date", timestamp).select("id");
                ForumEntity tmp = forumMapper.selectOne(queryWrapper);
                for (Map item: forumVo.getImgOrRadio()) {
                    String url = (String) item.get("url");
                    if (forumResourceService.addForumResource(tmp.getId(), url) <= 0)
                        return JsonResult.error("添加失败");
                }
                return JsonResult.success("success");
            }
            return JsonResult.error("添加失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    /**
     * Query all forum entities.
     *
     * @param request Http request
     * @return All the forum entities along with details
     */
    @GetMapping("/query")
    public JsonResult<List<TotalForum>> getForum(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        List<TotalForum> totalForums = new ArrayList<>();
        List<ForumEntity> forumEntities = forumService.getForum();
        for (ForumEntity forumEntity : forumEntities) {
            TotalForum totalForum = new TotalForum();
            totalForum.setId(forumEntity.getId());
            totalForum.setLocation(forumEntity.getLocation());
            totalForum.setContent(forumEntity.getContent());
            totalForum.setCreateDate(forumEntity.getCreateDate());
            totalForum.setNickname(userService.queryUserInfo(forumEntity.getOpenid()).getNickname());
            totalForum.setAvatarUrl(userService.queryUserInfo(forumEntity.getOpenid()).getAvatarUrl());
            totalForum.setImgOrRadio(forumResourceService.getFiles(forumEntity.getId()));
            totalForum.setLikes(forumLikeService.getLikeNames(forumEntity.getId()));
            totalForum.setComments(commentService.getComment(forumEntity.getId()));
            totalForums.add(totalForum);
        }
        return JsonResult.success(totalForums);
    }
}
