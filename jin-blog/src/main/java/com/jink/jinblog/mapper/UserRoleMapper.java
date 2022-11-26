package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 用户角色
 * @date 2022/11/26 17:12:00
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
