package com.jink.jinblog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JINK
 * @version 1.0
 * @description
 * @date 2023/3/5 19:56:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "博客信息")
public class BlogInfoVO {

    /**
     * 关于我内容
     */
    @Schema(name = "aboutContent", defaultValue = "关于我内容", required = true, type = "String")
    private String aboutContent;
}
