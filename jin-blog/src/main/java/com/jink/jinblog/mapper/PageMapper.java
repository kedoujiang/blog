package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 页面
 * @date 2022/11/26 16:56:34
 */
@Mapper
public interface PageMapper extends BaseMapper<Page> {
}
