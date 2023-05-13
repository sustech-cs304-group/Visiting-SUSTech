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
 * For comment operations.
 *
 * @author pound
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ForumService forumService;
    @Autowired
    private ForumMapper forumMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private JwtUtil jwtUtil;
    /**
     * Add a comment.
     *
     * @param commentVo Comment info to add
     * @param request Http request
     * @return Message of success or fail
     */
    @PostMapping("/add")
    public JsonResult<String> addComment(@RequestBody CommentVo commentVo,
                                       HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(commentVo, commentEntity);
        commentEntity.setOpenid(openid);
        commentEntity.setNickname(userService.queryUserInfo(openid).getNickname());
        try {
            if (commentService.addComment(commentEntity) > 0)
                return JsonResult.success("success");
            else
                return JsonResult.error("添加失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    /**
     * Delete a comment.
     *
     * @param id Comment id to delete
     * @param request Http request
     * @return Message of success or fail
     */
    @PostMapping("/delete")
    public JsonResult<Void> deleteComment(@RequestParam("id") Integer id,
                                              HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String openid = jwtUtil.getOpenidFromToken(token);
            if (commentService.deleteComment(openid, id) > 0)
                return JsonResult.success();
            else
                return JsonResult.error("删除失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    /**
     * Query all comments of a forum.
     *
     * @param request Http request
     * @param forumId Forum id for querying
     * @return All the comments of required forum.
     */
    @GetMapping("/query")
    public JsonResult<List<CommentEntity>> getComment(HttpServletRequest request, Integer forumId) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        return JsonResult.success(commentService.getComment(forumId));
    }
}
