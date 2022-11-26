package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 标签
 * @date 2022/11/26 17:10:07
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}
