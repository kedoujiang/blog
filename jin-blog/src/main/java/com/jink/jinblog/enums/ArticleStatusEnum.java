package com.jink.jinblog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author JINK
 * @version 1.0
 * @description 文章状态枚举
 * @date 2023/3/5 19:33:26
 */
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {
    /**
     * 公开
     */
    PUBLIC(1, "公开"),
    /**
     * 私密
     */
    SECRET(2, "私密"),
    /**
     * 草稿
     */
    DRAFT(3, "草稿");

    /**
     * 状态
     */
    private final Integer status;

    /**
     * 描述
     */
    private final String desc;
}
