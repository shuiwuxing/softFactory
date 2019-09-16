package com.yang.ess.business.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 库存数量 Entity
 *
 * @author MrBird
 * @date 2019-09-04 22:52:21
 */
@Data
@TableName("inventory")
public class Inventory {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    @TableField("sid")
    private Integer sid;

    /**
     * 库存数量
     */
    @TableField("num")
    private Integer num;

    /**
     * 预警数量
     */
    @TableField("warning_num")
    private Integer warningNum;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

}