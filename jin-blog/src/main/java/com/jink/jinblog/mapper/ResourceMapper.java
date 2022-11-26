package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 资源
 * @date 2022/11/26 17:07:16
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
}
