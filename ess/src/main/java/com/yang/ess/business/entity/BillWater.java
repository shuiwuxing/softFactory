package com.yang.ess.business.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 流水记录 Entity
 *
 * @author MrBird
 * @date 2019-09-04 22:51:56
 */
@Data
@TableName("bill_water")
public class BillWater {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 经办人
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 类型（1收入2支出）
     */
    @TableField("type")
    private Integer type;

    /**
     * 支出类型
     */
    @TableField("out_type")
    private Integer outType;

    /**
     * 收入类型
     */
    @TableField("in_type")
    private Integer inType;

    /**
     * 单据id
     */
    @TableField("whid")
    private Integer whid;

    /**
     * 金额
     */
    @TableField("money")
    private Integer money;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

}