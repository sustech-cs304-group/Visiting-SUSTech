package com.sustech.cs304.visitingsustech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sustech.cs304.visitingsustech.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * User info mapper for database operation.
 *
 * @author sui_h
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {

}
