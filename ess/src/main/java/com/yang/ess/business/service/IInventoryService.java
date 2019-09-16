package com.yang.ess.business.service;

import com.yang.ess.business.entity.Inventory;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 库存数量 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:21
 */
public interface IInventoryService extends IService<Inventory> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param inventory inventory
     * @return IPage<Inventory>
     */
    IPage<Inventory> findInventorys(QueryRequest request, Inventory inventory);

    /**
     * 查询（所有）
     *
     * @param inventory inventory
     * @return List<Inventory>
     */
    List<Inventory> findInventorys(Inventory inventory);

    /**
     * 新增
     *
     * @param inventory inventory
     */
    void createInventory(Inventory inventory);

    /**
     * 修改
     *
     * @param inventory inventory
     */
    void updateInventory(Inventory inventory);

    /**
     * 删除
     *
     * @param inventory inventory
     */
    void deleteInventory(Inventory inventory);
}
