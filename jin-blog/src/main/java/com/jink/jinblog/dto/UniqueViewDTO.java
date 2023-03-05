package com.jink.jinblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JINK
 * @version 1.0
 * @description 访问量
 * @date 2023/3/5 19:54:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniqueViewDTO {

    /**
     * 日期
     */
    private String day;

    /**
     * 访问量
     */
    private Integer viewsCount;
}
