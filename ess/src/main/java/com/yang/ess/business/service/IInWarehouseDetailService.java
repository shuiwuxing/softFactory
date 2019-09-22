package com.yang.ess.business.service;

import com.yang.ess.business.entity.InWarehouseDetail;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 入库单详情 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:17
 */
public interface IInWarehouseDetailService extends IService<InWarehouseDetail> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param inWarehouseDetail inWarehouseDetail
     * @return IPage<InWarehouseDetail>
     */
    IPage<Map<String,Object>> findInWarehouseDetailsIn(QueryRequest request, InWarehouseDetail inWarehouseDetail);

    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param inWarehouseDetail inWarehouseDetail
     * @return IPage<InWarehouseDetail>
     */
    IPage<InWarehouseDetail> findInWarehouseDetails(QueryRequest request, InWarehouseDetail inWarehouseDetail);

    /**
     * 查询（所有）
     *
     * @param inWarehouseDetail inWarehouseDetail
     * @return List<InWarehouseDetail>
     */
    List<InWarehouseDetail> findInWarehouseDetails(InWarehouseDetail inWarehouseDetail);

    /**
     * 查询
     *
     * @param   wid  String
     * @return List<OutWarehouseDetail>
     */
    List<Map<String,Object>> selectInWarehouseDetail(String wid);

    /**
     * 新增
     *
     * @param inWarehouseDetail inWarehouseDetail
     */
    void createInWarehouseDetail(InWarehouseDetail inWarehouseDetail);

    /**
     * 修改
     *
     * @param inWarehouseDetail inWarehouseDetail
     */
    void updateInWarehouseDetail(InWarehouseDetail inWarehouseDetail);

    /**
     * 删除
     *
     * @param inWarehouseDetail inWarehouseDetail
     */
    void deleteInWarehouseDetail(InWarehouseDetail inWarehouseDetail);
}
