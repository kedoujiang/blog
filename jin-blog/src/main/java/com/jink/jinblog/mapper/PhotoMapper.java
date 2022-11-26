package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 照片映射
 * @date 2022/11/26 17:01:14
 */
@Mapper
public interface PhotoMapper extends BaseMapper<Photo> {
}
