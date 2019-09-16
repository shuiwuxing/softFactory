package com.yang.ess.business.service;

import com.yang.ess.business.entity.BillWater;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 流水记录 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:51:56
 */
public interface IBillWaterService extends IService<BillWater> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param billWater billWater
     * @return IPage<BillWater>
     */
    IPage<BillWater> findBillWaters(QueryRequest request, BillWater billWater);

    /**
     * 查询（所有）
     *
     * @param billWater billWater
     * @return List<BillWater>
     */
    List<BillWater> findBillWaters(BillWater billWater);

    /**
     * 新增
     *
     * @param billWater billWater
     */
    void createBillWater(BillWater billWater);

    /**
     * 修改
     *
     * @param billWater billWater
     */
    void updateBillWater(BillWater billWater);

    /**
     * 删除
     *
     * @param billWater billWater
     */
    void deleteBillWater(BillWater billWater);
}
