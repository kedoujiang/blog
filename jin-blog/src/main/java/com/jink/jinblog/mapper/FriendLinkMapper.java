package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.FriendLink;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 友情链接
 * @date 2022/11/26 15:59:26
 */
@Mapper
public interface FriendLinkMapper extends BaseMapper<FriendLink> {
}
