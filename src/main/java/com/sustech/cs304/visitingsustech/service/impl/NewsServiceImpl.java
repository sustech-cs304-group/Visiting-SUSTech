package com.sustech.cs304.visitingsustech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sustech.cs304.visitingsustech.entity.NewsEntity;
import com.sustech.cs304.visitingsustech.mapper.NewsMapper;
import com.sustech.cs304.visitingsustech.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of news service.
 *
 * @author pound
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, NewsEntity> implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public boolean createNews(NewsEntity newsEntity) {
        return newsMapper.insert(newsEntity) > 0;
    }

    @Override
    public List<NewsEntity> getAllNews() {
        QueryWrapper<NewsEntity> queryWrapper = new QueryWrapper<>();
        return newsMapper.selectList(queryWrapper);
    }


}
