package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 权限
 * @date 2022/11/26 17:07:49
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
