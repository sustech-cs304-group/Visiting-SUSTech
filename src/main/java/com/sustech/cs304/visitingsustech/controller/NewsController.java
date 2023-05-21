package com.sustech.cs304.visitingsustech.controller;

import com.sustech.cs304.visitingsustech.common.JsonResult;
import com.sustech.cs304.visitingsustech.entity.NewsEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import com.sustech.cs304.visitingsustech.exception.BaseException;
import com.sustech.cs304.visitingsustech.service.NewsService;
import com.sustech.cs304.visitingsustech.service.UserService;
import com.sustech.cs304.visitingsustech.util.JwtUtil;
import com.sustech.cs304.visitingsustech.vo.NewsVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * For news operations.
 *
 * @author pound
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    /**
     * Create news.
     *
     * @param newsVo Info of news to add
     * @param request Http request
     * @return Message of success or fail
     */
    @PostMapping("/create")
    public JsonResult<String> createNews(@RequestBody NewsVo newsVo,
                                         HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = jwtUtil.getOpenidFromToken(token);
        UserInfoEntity userInfoEntity = userService.queryUserInfo(openid);
        if (userInfoEntity == null || !userInfoEntity.getType().equals("admin"))
            return JsonResult.error(403,"权限不足");
        NewsEntity newsEntity = new NewsEntity();
        BeanUtils.copyProperties(newsVo, newsEntity);
        long time = System.currentTimeMillis();
        newsEntity.setCreateTime(new Timestamp(time));
        newsEntity.setUpdateTime(new Timestamp(time));
        try {
            if (newsService.createNews(newsEntity))
                return JsonResult.success("success");
            else
                return JsonResult.error("添加失败");
        } catch (BaseException e) {
            return JsonResult.error(e.getStatus(), e.getMessage());
        }
    }
    /**
     * Query all news.
     *
     * @return List of all news.
     */
    @GetMapping("/list")
    public JsonResult<List<NewsEntity>> listNews() {
        return JsonResult.success(newsService.getAllNews());
    }
}
