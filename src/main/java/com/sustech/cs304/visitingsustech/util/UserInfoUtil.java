package com.sustech.cs304.visitingsustech.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class UserInfoUtil {
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String WX_LOGIN_APPID = "wx64166ba63297087b";
    public static final String WX_LOGIN_SECRET = "bfc933b4c7c24ef263bc360580a7e35a";
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode getResultJson(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("appid", WX_LOGIN_APPID);
        params.put("secret", WX_LOGIN_SECRET);
        params.put("js_code", code);
        params.put("grant_type", WX_LOGIN_GRANT_TYPE);
        String wxRequestResult = HttpClientUtil.doGet(WX_LOGIN_URL, params);
        try {
            return objectMapper.readTree(wxRequestResult);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getOpenid(String code) {
        return getResultJson(code).get("openid").asText();
    }
}