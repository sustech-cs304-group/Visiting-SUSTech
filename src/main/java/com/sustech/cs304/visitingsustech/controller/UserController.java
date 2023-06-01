package com.sustech.cs304.visitingsustech.controller;

import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import com.sustech.cs304.visitingsustech.service.UserService;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.vo.UserInfoVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * For user operations.
 *
 * @author pound
 */
@RestController
@RequestMapping("/user/person-info")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${spring.servlet.multipart.location}")
    private String path;
    /**
     * Add a user.
     *
     * @param userInfoVo Info of user to update
     * @param request Http request
     * @return Message of success or fail
     */
    @PostMapping("/update")
    public JsonResult<Void> updateUserInfo(HttpServletRequest request, @RequestBody UserInfoVo userInfoVo) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        try {
            if (userService.updateUserInfo(openid, userInfoVo) > 0)
                return JsonResult.success("修改个人信息成功");
            else
                return JsonResult.error("修改个人信息失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }
    /**
     * Update an avatar.
     *
     * @param avatar Avatar to update
     * @param request Http request
     * @return Message of success or fail
     */
    @PostMapping("/update-avatar")
    public JsonResult<String> updateAvatar(HttpServletRequest request, @RequestParam("avatar") MultipartFile avatar) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        if (avatar.isEmpty())
            return JsonResult.error("上传头像失败");
        try {
            File file = new File(path);
            if (!file.exists())
                file.mkdirs();
            String suffix = Objects.requireNonNull(avatar.getOriginalFilename()).substring(avatar.getOriginalFilename().lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            avatar.transferTo(new File(path + newFileName));
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + newFileName;
            if (userService.updateAvatar(openid, url) > 0)
                return JsonResult.success(200, "修改头像成功", url);
            else
                return JsonResult.error("修改头像失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        } catch (IOException e) {
            return JsonResult.error("上传头像失败");
        }
    }
    /**
     * Query user info.
     *
     * @param request Http request
     * @return Info of queried user.
     */
    @GetMapping("/query")
    public JsonResult<UserInfoEntity> getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        UserInfoEntity userInfoEntity = userService.queryUserInfo(openid);
        if (userInfoEntity != null)
            return JsonResult.success(userInfoEntity);
        else
            return JsonResult.error(400, "查询个人信息失败");
    }
}
