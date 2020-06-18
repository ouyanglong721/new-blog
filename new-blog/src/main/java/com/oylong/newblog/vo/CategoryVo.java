package com.oylong.newblog.vo;

import lombok.Data;

@Data
public class CategoryVo {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private Integer childrenCount;
}
