package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 留言
 * @date 2022/11/26 16:07:26
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
