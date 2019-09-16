package com.yang.ess.business.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 出库单 Entity
 *
 * @author MrBird
 * @date 2019-09-04 22:52:30
 */
@Data
@TableName("out_warehouse")
public class OutWarehouse {

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
     * 客户id
     */
    @TableField("cid")
    private Integer cid;

    /**
     * 金额
     */
    @TableField("money")
    private Integer money;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd") //入参
    @TableField("create_time")
    private Date createTime;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;


}