package com.yang.ess.business.service.impl;

import com.yang.ess.business.entity.BillWater;
import com.yang.ess.business.mapper.BillWaterMapper;
import com.yang.ess.business.service.IBillWaterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
/**
 * 流水记录 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:51:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BillWaterServiceImpl extends ServiceImpl<BillWaterMapper, BillWater> implements IBillWaterService {

    @Autowired
    private BillWaterMapper billWaterMapper;

    @Override
    public IPage<BillWater> findBillWaters(QueryRequest request, BillWater billWater) {
        LambdaQueryWrapper<BillWater> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<BillWater> page = new Page<BillWater>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<BillWater> findBillWaters(BillWater billWater) {
	    LambdaQueryWrapper<BillWater> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createBillWater(BillWater billWater) {
        this.save(billWater);
    }

    @Override
    @Transactional
    public void updateBillWater(BillWater billWater) {
        this.saveOrUpdate(billWater);
    }

    @Override
    @Transactional
    public void deleteBillWater(BillWater billWater) {
        LambdaQueryWrapper<BillWater> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
