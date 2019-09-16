package com.yang.ess.business.service;

import com.yang.ess.business.entity.OutWarehouse;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 出库单 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:30
 */
public interface IOutWarehouseService extends IService<OutWarehouse> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param outWarehouse outWarehouse
     * @return IPage<OutWarehouse>
     */
    IPage<OutWarehouse> findOutWarehouses(QueryRequest request, OutWarehouse outWarehouse);

    /**
     * 查询（所有）
     *
     * @param outWarehouse outWarehouse
     * @return List<OutWarehouse>
     */
    List<OutWarehouse> findOutWarehouses(OutWarehouse outWarehouse);

    /**
     * 新增
     *
     * @param outWarehouse outWarehouse
     */
    void createOutWarehouse(OutWarehouse outWarehouse,String detail,String Name,String Phone);

    /**
     * 修改
     *
     * @param outWarehouse outWarehouse
     */
    void updateOutWarehouse(OutWarehouse outWarehouse);

    /**
     * 删除
     *
     * @param outWarehouse outWarehouse
     */
    void deleteOutWarehouse(OutWarehouse outWarehouse);
}
