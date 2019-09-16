package com.yang.ess.business.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 入库单详情 Entity
 *
 * @author MrBird
 * @date 2019-09-04 22:52:17
 */
@Data
@TableName("in_warehouse_detail")
public class InWarehouseDetail {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 出库单id
     */
    @TableField("wid")
    private Integer wid;
    /**
     * 出库单id
     */
    @TableField("type")
    private Integer type;

    /**
     * 商品id
     */
    @TableField("gid")
    private Integer gid;

    /**
     * 供应商id
     */
    @TableField("sid")
    private Integer sid;

    /**
     * 手机串号
     */
    @TableField("imei")
    private String imei;

    /**
     * 单价
     */
    @TableField("money")
    private Integer money;

    /**
     * 数量
     */
    @TableField("num")
    private Integer num;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 地址
     */
    @TableField("update_time")
    private Date updateTime;

}