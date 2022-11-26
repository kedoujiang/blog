package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.ChatRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 聊天记录
 * @date 2022/11/26 15:56:34
 */
@Mapper
public interface ChatRecordMapper extends BaseMapper<ChatRecord> {
}
