package com.yang.ess.business.service.impl;

import com.yang.ess.business.entity.OutWarehouseDetail;
import com.yang.ess.business.mapper.OutWarehouseDetailMapper;
import com.yang.ess.business.service.IOutWarehouseDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 出库单详情 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:33
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OutWarehouseDetailServiceImpl extends ServiceImpl<OutWarehouseDetailMapper, OutWarehouseDetail> implements IOutWarehouseDetailService {

    @Autowired
    private OutWarehouseDetailMapper outWarehouseDetailMapper;

    @Override
    public IPage<OutWarehouseDetail> findOutWarehouseDetails(QueryRequest request, OutWarehouseDetail outWarehouseDetail) {
        LambdaQueryWrapper<OutWarehouseDetail> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<OutWarehouseDetail> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<OutWarehouseDetail> findOutWarehouseDetails(OutWarehouseDetail outWarehouseDetail) {
	    LambdaQueryWrapper<OutWarehouseDetail> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
        if(outWarehouseDetail.getWid()!=null){
            queryWrapper.eq(OutWarehouseDetail::getWid,outWarehouseDetail.getWid());
        }
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String,Object>> selectOutWarehouseDetail(String wid) {
        Map<String,Object> condition=new HashMap<>();
        condition.put("wid",wid);
        return outWarehouseDetailMapper.selectOutWarehouseDetail(condition);
    }

    @Override
    @Transactional
    public void createOutWarehouseDetail(OutWarehouseDetail outWarehouseDetail) {
        this.save(outWarehouseDetail);
    }

    @Override
    @Transactional
    public void updateOutWarehouseDetail(OutWarehouseDetail outWarehouseDetail) {
        this.saveOrUpdate(outWarehouseDetail);
    }

    @Override
    @Transactional
    public void deleteOutWarehouseDetail(OutWarehouseDetail outWarehouseDetail) {
        LambdaQueryWrapper<OutWarehouseDetail> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
