package com.jink.jinblog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author JINK
 * @version 1.0
 * @description 标签
 * @date 2023/3/5 19:52:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    /**
     * id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;
}
