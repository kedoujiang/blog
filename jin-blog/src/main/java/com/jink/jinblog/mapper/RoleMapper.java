package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 权限
 * @date 2022/11/26 17:07:49
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id获取角色列表
     *
     * @param userInfoId 用户id
     * @return 角色标签
     */
    List<String> listRolesByUserInfoId(Integer userInfoId);

    /**
     *  列出所以角色标签
     * @return 角色标签
     */
    List<String> listRolesLabel();
}
