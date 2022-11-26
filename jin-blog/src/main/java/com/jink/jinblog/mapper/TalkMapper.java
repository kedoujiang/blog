package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.Talk;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 说说
 * @date 2022/11/26 17:10:36
 */
@Mapper
public interface TalkMapper extends BaseMapper<Talk> {
}
