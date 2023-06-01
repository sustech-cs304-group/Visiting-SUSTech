package com.sustech.cs304.visitingsustech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sustech.cs304.visitingsustech.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * News mapper for database operation.
 *
 * @author sui_h
 */
@Mapper
public interface NewsMapper extends BaseMapper<NewsEntity> {
}
