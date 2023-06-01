package com.sustech.cs304.visitingsustech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sustech.cs304.visitingsustech.entity.ForumResourceEntity;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Forum resource mapper for database operation.
 *
 * @author pound
 */
@Mapper
public interface ForumResourceMapper extends BaseMapper<ForumResourceEntity> {

}
