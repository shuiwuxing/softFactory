package com.yang.ess.business.service;

import com.yang.ess.business.entity.InWarehouse;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 入库单 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:13
 */
public interface IInWarehouseService extends IService<InWarehouse> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param inWarehouse inWarehouse
     * @return IPage<InWarehouse>
     */
    IPage<InWarehouse> findInWarehouses(QueryRequest request, InWarehouse inWarehouse);

    /**
     * 查询（所有）
     *
     * @param inWarehouse inWarehouse
     * @return List<InWarehouse>
     */
    List<InWarehouse> findInWarehouses(InWarehouse inWarehouse);

    /**
     * 新增
     *
     * @param inWarehouse inWarehouse
     */
    void createInWarehouse(InWarehouse inWarehouse,String details);

    /**
     * 修改
     *
     * @param inWarehouse inWarehouse
     */
    void updateInWarehouse(InWarehouse inWarehouse);

    /**
     * 删除
     *
     * @param inWarehouse inWarehouse
     */
    void deleteInWarehouse(InWarehouse inWarehouse);
}
