package com.yang.ess.business.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 入库单 Entity
 *
 * @author MrBird
 * @date 2019-09-04 22:52:13
 */
@Data
@TableName("in_warehouse")
public class InWarehouse {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 经办人
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 总金额
     */
    @TableField("money")
    private Integer money;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

}