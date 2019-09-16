package com.yang.ess.business.service;

import com.yang.ess.business.entity.OutWarehouseDetail;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 出库单详情 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:33
 */
public interface IOutWarehouseDetailService extends IService<OutWarehouseDetail> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param outWarehouseDetail outWarehouseDetail
     * @return IPage<OutWarehouseDetail>
     */
    IPage<OutWarehouseDetail> findOutWarehouseDetails(QueryRequest request, OutWarehouseDetail outWarehouseDetail);

    /**
     * 查询（所有）
     *
     * @param outWarehouseDetail outWarehouseDetail
     * @return List<OutWarehouseDetail>
     */
    List<OutWarehouseDetail> findOutWarehouseDetails(OutWarehouseDetail outWarehouseDetail);

    /**
     * 查询
     *
     * @param   wid  String
     * @return List<OutWarehouseDetail>
     */
    List<Map<String,Object>> selectOutWarehouseDetail(String wid);

    /**
     * 新增
     *
     * @param outWarehouseDetail outWarehouseDetail
     */
    void createOutWarehouseDetail(OutWarehouseDetail outWarehouseDetail);

    /**
     * 修改
     *
     * @param outWarehouseDetail outWarehouseDetail
     */
    void updateOutWarehouseDetail(OutWarehouseDetail outWarehouseDetail);

    /**
     * 删除
     *
     * @param outWarehouseDetail outWarehouseDetail
     */
    void deleteOutWarehouseDetail(OutWarehouseDetail outWarehouseDetail);
}
