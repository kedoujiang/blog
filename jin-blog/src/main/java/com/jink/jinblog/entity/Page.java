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
 * @description 页面
 * @date 2022/11/26 15:45:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value ="tb_page")
public class Page {

    /**
     * 页面id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 页面名
     */
    private String pageName;

    /**
     * 页面标签
     */
    private String pageLabel;

    /**
     * 页面封面
     */
    private String pageCover;

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
