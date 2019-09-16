package com.yang.ess.business.service;

import com.yang.ess.business.entity.Supplier;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 供应商 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:37
 */
public interface ISupplierService extends IService<Supplier> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param supplier supplier
     * @return IPage<Supplier>
     */
    IPage<Supplier> findSuppliers(QueryRequest request, Supplier supplier);

    /**
     * 查询（所有）
     *
     * @param supplier supplier
     * @return List<Supplier>
     */
    List<Supplier> findSuppliers(Supplier supplier);

    /**
     * 新增
     *
     * @param supplier supplier
     */
    void createSupplier(Supplier supplier);

    /**
     * 修改
     *
     * @param supplier supplier
     */
    void updateSupplier(Supplier supplier);

    /**
     * 删除
     *
     * @param supplier supplier
     */
    void deleteSupplier(Supplier supplier);
}
