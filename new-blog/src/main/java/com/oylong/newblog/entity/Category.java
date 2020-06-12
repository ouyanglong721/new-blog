package com.oylong.newblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;


@Data
public class Category {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long parentId;

    @TableField(exist = false)
    private List<Category> chidren;
}
