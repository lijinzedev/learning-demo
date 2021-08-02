package com.curiosity.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Classname User2
 * @Description
 * @Date 2021/8/2 14:13
 * @Created by lijinze
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user2")
public class Son {
    @TableId(value = "id", type = IdType.NONE)
    private Integer id;
    @TableField("name")
    private String name;
}
