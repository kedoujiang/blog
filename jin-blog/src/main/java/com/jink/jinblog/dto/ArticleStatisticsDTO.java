package com.jink.jinblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JINK
 * @version 1.0
 * @description
 * @date 2023/3/5 19:53:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleStatisticsDTO {

    /**
     * 日期
     */
    private String date;

    /**
     * 数量
     */
    private Integer count;
}
