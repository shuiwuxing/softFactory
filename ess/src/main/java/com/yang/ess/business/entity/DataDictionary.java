package com.yang.ess.business.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 数据字典 Entity
 *
 * @author MrBird
 * @date 2019-09-04 22:52:09
 */
@Data
@TableName("data_dictionary")
public class DataDictionary {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 字典类型
     */
    @TableField("type")
    private String type;

    /**
     * 显示
     */
    @TableField("text")
    private String text;

    /**
     * 值
     */
    @TableField("value")
    private Integer value;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

}