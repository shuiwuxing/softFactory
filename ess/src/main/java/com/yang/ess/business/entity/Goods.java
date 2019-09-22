package com.yang.ess.business.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 商品表 Entity
 *
 * @author MrBird
 * @date 2019-09-04 22:52:04
 */
@Data
@TableName("goods")
public class Goods {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;
    /**
     * 品牌
     */
    @TableField("brand")
    private String brand;
    /**
     * 型号
     */
    @TableField("model")
    private String model;
    /**
     * 颜色
     */
    @TableField("color")
    private String color;

    /**
     * 商品类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

}