package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.PhotoAlbum;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 相册映射器
 * @date 2022/11/26 16:58:46
 */
@Mapper
public interface PhotoAlbumMapper extends BaseMapper<PhotoAlbum> {
}
