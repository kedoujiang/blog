package com.jink.jinblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jink.jinblog.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 操作日志
 * @date 2022/11/26 16:08:04
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
}
