package com.yang.ess.business.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 备忘录 Entity
 *
 * @author MrBird
 * @date 2019-09-04 22:52:25
 */
@Data
@TableName("memo")
public class Memo {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 描述
     */
    @TableField("content")
    private String content;

    /**
     * 经办人
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 预计处理时间
     */
    @TableField("expect_time")
    private Date expectTime;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

}