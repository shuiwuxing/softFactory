package com.yang.ess.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.yang.ess.business.entity.InWarehouse;
import com.yang.ess.business.entity.InWarehouseDetail;
import com.yang.ess.business.mapper.InWarehouseMapper;
import com.yang.ess.business.service.IInWarehouseDetailService;
import com.yang.ess.business.service.IInWarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 入库单 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class InWarehouseServiceImpl extends ServiceImpl<InWarehouseMapper, InWarehouse> implements IInWarehouseService {

    @Autowired
    private InWarehouseMapper inWarehouseMapper;

    @Autowired
    IInWarehouseDetailService inWarehouseDetailService;

    @Override
    public IPage<InWarehouse> findInWarehouses(QueryRequest request, InWarehouse inWarehouse) {
        LambdaQueryWrapper<InWarehouse> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        if(inWarehouse.getOrderNo()!=null){
            queryWrapper.like(InWarehouse::getOrderNo,inWarehouse.getOrderNo());
        }
        if(inWarehouse.getStatus()!=null){
            queryWrapper.like(InWarehouse::getStatus,inWarehouse.getStatus());
        }
        queryWrapper.orderByDesc(InWarehouse::getId);
        Page<InWarehouse> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<InWarehouse> findInWarehouses(InWarehouse inWarehouse) {
	    LambdaQueryWrapper<InWarehouse> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createInWarehouse(InWarehouse inWarehouse,String detail) {
        inWarehouse.setCreateTime(new Date());
        inWarehouse.setStatus(1);
        this.save(inWarehouse);
        List<Map> details= JSON.parseArray(detail, Map.class);
        for (int i=0;i<details.size();i++){
            InWarehouseDetail inWarehouseDetail =new InWarehouseDetail();
            inWarehouseDetail.setWid(inWarehouse.getId());
            String startChar=details.get(i).get("imei").toString().substring(0,1);
            if("a".equals(startChar)){
                inWarehouseDetail.setType(2);
            }else if("b".equals(startChar)){
                inWarehouseDetail.setType(3);
            }else{
                inWarehouseDetail.setType(1);
            }
            inWarehouseDetail.setGid(Integer.parseInt(details.get(i).get("goodsId").toString()));
            inWarehouseDetail.setImei(details.get(i).get("imei").toString());
            inWarehouseDetail.setMoney(Integer.parseInt(details.get(i).get("price").toString()));
            inWarehouseDetail.setNum(Integer.parseInt(details.get(i).get("num").toString()));
            inWarehouseDetail.setUpdateTime(new Date());
            inWarehouseDetail.setStatus(1);
            inWarehouseDetail.setStatus(1);
            inWarehouseDetailService.save(inWarehouseDetail);
        }
    }

    @Override
    @Transactional
    public void updateInWarehouse(InWarehouse inWarehouse) {
        this.saveOrUpdate(inWarehouse);
    }

    @Override
    @Transactional
    public void deleteInWarehouse(InWarehouse inWarehouse) {
        LambdaQueryWrapper<InWarehouse> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
