package com.jink.jinblog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author JINK
 * @version 1.0
 * @description 文章
 * @date 2023/3/5 19:28:50
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "文章")
public class ArticleVO {

    /**
     * 文章id
     */
    @Schema(name = "id", defaultValue = "文章id", type = "Integer")
    private Integer id;

    /**
     * 标题
     */
    @NotBlank(message = "文章标题不能为空")
    @Schema(name = "articleTitle", defaultValue = "文章标题", required = true, type = "String")
    private String articleTitle;

    /**
     * 内容
     */
    @NotBlank(message = "文章内容不能为空")
    @Schema(name = "articleContent", defaultValue = "文章内容", required = true, type = "String")
    private String articleContent;

    /**
     * 文章封面
     */
    @Schema(name = "articleCover", defaultValue = "文章缩略图", type = "String")
    private String articleCover;

    /**
     * 文章分类
     */
    @Schema(name = "category", defaultValue = "文章分类", type = "Integer")
    private String categoryName;

    /**
     * 文章标签
     */
    @Schema(name = "tagNameList", defaultValue = "文章标签", type = "List<Integer>")
    private List<String> tagNameList;

    /**
     * 文章类型
     */
    @Schema(name = "type", defaultValue = "文章类型", type = "Integer")
    private Integer type;

    /**
     * 原文链接
     */
    @Schema(name = "originalUrl", defaultValue = "原文链接", type = "String")
    private String originalUrl;

    /**
     * 是否置顶
     */
    @Schema(name = "isTop", defaultValue = "是否置顶", type = "Integer")
    private Integer isTop;

    /**
     * 文章状态 1.公开 2.私密 3.评论可见
     */
    @Schema(name = "status", defaultValue = "文章状态", type = "String")
    private Integer status;
}
