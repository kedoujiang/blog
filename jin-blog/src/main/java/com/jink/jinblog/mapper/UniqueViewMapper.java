package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.UniqueView;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 访问量
 * @date 2022/11/26 17:11:20
 */
@Mapper
public interface UniqueViewMapper extends BaseMapper<UniqueView> {
}
