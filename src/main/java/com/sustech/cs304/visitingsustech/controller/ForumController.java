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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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

    @PostMapping("/add")
    public JsonResult<String> addForum(@RequestBody ForumVo forumVo,
                                             HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        ForumEntity forumEntity = new ForumEntity();
        forumEntity.setOpenid(openid);
        forumEntity.setLocation(forumVo.getLocation());
        forumEntity.setContent(forumVo.getContent());
        forumEntity.setCreateDate(forumVo.getCreateDate());
        try {
            if (forumService.addForum(forumEntity) > 0) {
//                QueryWrapper<ForumEntity> queryWrapper = new QueryWrapper<>();
//                queryWrapper.eq("openid", openid).eq("create_date", forumVo.getCreateDate()).select("id");
//                ForumEntity tmp = forumMapper.selectOne(queryWrapper);
//                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/";
//                int res = addForumResource(forumVo.getImgOrRadio(), tmp.getId(), url).getCode();
//                if (res == 200)
//                    return JsonResult.success("success");
                return JsonResult.success("success");
            }
            return JsonResult.error("添加失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }

    }

    @PostMapping("/delete")
    public JsonResult<Void> deleteForum(@RequestParam("id") Integer id,
                                              HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String openid = jwtUtil.getOpenidFromToken(token);
            if (forumService.deleteForum(openid, id) > 0)
                return JsonResult.success();
            else
                return JsonResult.error("删除失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/query")
    public JsonResult<List<ForumEntity>> getForum(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
//        List<ForumEntity> forumEntities = forumService.getForum(openid);
//        for (ForumEntity forumEntity : forumEntities) {
//            List<ForumResourceEntity> forumResourceEntities = forumResourceService.getForumResource(forumEntity.getId());
//            List<CommentEntity> commentEntities = commentService.getComment(forumEntity.getId());
//            List<ForumLikeEntity> forumLikeEntities = forumLikeService.getForumLike(forumEntity.getId());
//        }
        return JsonResult.success(forumService.getForum(openid));
    }
}
