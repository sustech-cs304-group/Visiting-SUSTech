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
import com.sustech.cs304.visitingsustech.vo.CommentVo;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * For forumLike operations.
 *
 * @author pound
 */
@RestController
@RequestMapping("/forumlike")
public class ForumLikeController {
    @Autowired
    private ForumService forumService;
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ForumLikeService forumLikeService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Add a like to a forum.
     *
     * @param forumId Forum id for specifying
     * @param request Http request
     * @return Message of success or fail
     */
    @PostMapping("/add")
    public JsonResult<String> addForumLike(@RequestParam("forumId") Integer forumId,
                                         HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        ForumLikeEntity forumLikeEntity = new ForumLikeEntity();
        forumLikeEntity.setOpenid(openid);
        forumLikeEntity.setNickname(userService.queryUserInfo(openid).getNickname());
        forumLikeEntity.setForumId(forumId);
        try {
            if (forumLikeService.addForumLike(forumLikeEntity) > 0)
                return JsonResult.success("success");
            else
                return JsonResult.error("添加失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    /**
     * Delete a like to a forum.
     *
     * @param id ForumLike id to delete
     * @param request Http request
     * @return Message of success or fail
     */
    @PostMapping("/delete")
    public JsonResult<Void> deleteForumLike(@RequestParam("id") Integer id,
                                          HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String openid = jwtUtil.getOpenidFromToken(token);
            if (forumLikeService.deleteForumLike(openid, id) > 0)
                return JsonResult.success();
            else
                return JsonResult.error("删除失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    /**
     * Query all likes of a forum.
     *
     * @param request Http request
     * @param forumId Forum id for querying
     * @return All the likes of required forum.
     */
    @GetMapping("/query")
    public JsonResult<List<ForumLikeEntity>> getForumLike(HttpServletRequest request, Integer forumId) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        return JsonResult.success(forumLikeService.getForumLike(forumId));
    }
}
