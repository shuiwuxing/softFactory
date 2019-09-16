package com.yang.ess.business.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 出库单详情 Entity
 *
 * @author MrBird
 * @date 2019-09-04 22:52:33
 */
@Data
@TableName("out_warehouse_detail")
public class OutWarehouseDetail {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    @TableField("gid")
    private Integer gid;

    /**
     * 出库单id
     */
    @TableField("wid")
    private Integer wid;

    /**
     * 手机串号
     */
    @TableField("imei")
    private String imei;

    /**
     * 数量
     */
    @TableField("num")
    private Integer num;

    /**
     * 金额
     */
    @TableField("money")
    private Integer money;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 类型 1手机 2维修 3手机配饰
     */
    @TableField("type")
    private Integer type;

}