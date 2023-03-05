package com.jink.jinblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 文章标签
 * @date 2022/11/26 15:38:26
 */
@Data
@Builder
public class ArticleTag {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 标签id
     */
    private Integer tagId;
}
