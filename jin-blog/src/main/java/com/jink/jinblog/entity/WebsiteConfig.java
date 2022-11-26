package com.jink.jinblog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 网站配置
 * @date 2022/11/26 15:52:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "tb_website_config")
public class WebsiteConfig {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 配置信息
     */
    private String config;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
