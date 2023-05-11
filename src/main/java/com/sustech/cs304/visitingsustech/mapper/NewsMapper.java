package com.sustech.cs304.visitingsustech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sustech.cs304.visitingsustech.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper extends BaseMapper<NewsEntity> {
}