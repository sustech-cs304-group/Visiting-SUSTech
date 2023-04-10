package com.sustech.cs304.visitingsustech.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.service.UserService;
import com.sustech.cs304.visitingsustech.util.EncryptUtil;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.util.UserInfoUtil;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class WxLoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/index/login")
    public JsonResult<Map<String, String>> authorizeLogin(@NotBlank @RequestParam("code") String code) {
        JsonNode jsonResult = UserInfoUtil.getResultJson(code);
        if (jsonResult.has("openid")) {
            String sessionKey = jsonResult.get("session_key").asText();
            String openid = jsonResult.get("openid").asText();
            String session;
            Map<String, String> sessionMap = new HashMap<>();
            sessionMap.put("openid", openid);
            sessionMap.put("session_key", sessionKey);
            try {
                session = objectMapper.writeValueAsString(sessionMap);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            try {
                EncryptUtil encryptUtil = new EncryptUtil();
                session = encryptUtil.encrypt(session);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String token = jwtUtil.getToken(session);
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            UserInfoEntity userInfoEntity = userService.queryUserInfo(openid);
            if (userInfoEntity != null)
                return JsonResult.success("登录成功", result);
            else {
                int rs = userService.insertUser(openid);
                if (rs <= 0)
                    return JsonResult.error("登录失败");
                else
                    return JsonResult.success("登录成功", result);
            }
        }
        return JsonResult.error("授权失败：" + jsonResult.get("errmsg").asText());
    }
}
