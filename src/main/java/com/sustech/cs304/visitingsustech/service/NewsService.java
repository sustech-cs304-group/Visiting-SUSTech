package com.sustech.cs304.visitingsustech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sustech.cs304.visitingsustech.entity.NewsEntity;

import java.util.List;

public interface NewsService extends IService<NewsEntity> {
    boolean createNews(NewsEntity newsEntity);

    List<NewsEntity> getAllNews();
}
