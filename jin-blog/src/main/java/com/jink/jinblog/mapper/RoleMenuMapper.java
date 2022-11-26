package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 权限菜单
 * @date 2022/11/26 17:08:14
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
}
