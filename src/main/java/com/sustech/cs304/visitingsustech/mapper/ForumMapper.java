package com.sustech.cs304.visitingsustech.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sustech.cs304.visitingsustech.entity.ForumEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Forum mapper for database operation.
 *
 * @author pound
 */
@Mapper
public interface ForumMapper extends BaseMapper<ForumEntity> {

}
