package com.yang.ess.business.service.impl;

import com.yang.ess.business.entity.InWarehouseDetail;
import com.yang.ess.business.mapper.InWarehouseDetailMapper;
import com.yang.ess.business.service.IInWarehouseDetailService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 入库单详情 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:17
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class InWarehouseDetailServiceImpl extends ServiceImpl<InWarehouseDetailMapper, InWarehouseDetail> implements IInWarehouseDetailService {

    @Autowired
    private InWarehouseDetailMapper inWarehouseDetailMapper;

    @Override
    public IPage<InWarehouseDetail> findInWarehouseDetails(QueryRequest request, InWarehouseDetail inWarehouseDetail) {
        LambdaQueryWrapper<InWarehouseDetail> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<InWarehouseDetail> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<Map<String,Object>> findInWarehouseDetailsIn(QueryRequest request, InWarehouseDetail inWarehouseDetail) {
       Map<String,Object> condition=new HashMap<>();
       condition.put("name",inWarehouseDetail.getAddress());
        // TODO 设置查询条件
        Page<InWarehouseDetail> page = new Page<>(request.getPageNum(), request.getPageSize());
        return inWarehouseDetailMapper.findInWareHouse(page,condition);
    }

    @Override
    public List<InWarehouseDetail> findInWarehouseDetails(InWarehouseDetail inWarehouseDetail) {
	    LambdaQueryWrapper<InWarehouseDetail> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
        if(inWarehouseDetail.getImei()!=null){
            queryWrapper.eq(InWarehouseDetail::getImei,inWarehouseDetail.getImei());
        }
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createInWarehouseDetail(InWarehouseDetail inWarehouseDetail) {
        this.save(inWarehouseDetail);
    }

    @Override
    @Transactional
    public void updateInWarehouseDetail(InWarehouseDetail inWarehouseDetail) {
        InWarehouseDetail inWarehouseDetailNew= this.getById(inWarehouseDetail.getId());
        inWarehouseDetailNew.setUpdateTime(new Date());
        inWarehouseDetailNew.setAddress(inWarehouseDetail.getAddress());
        this.saveOrUpdate(inWarehouseDetailNew);
    }

    @Override
    @Transactional
    public void deleteInWarehouseDetail(InWarehouseDetail inWarehouseDetail) {
        LambdaQueryWrapper<InWarehouseDetail> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
