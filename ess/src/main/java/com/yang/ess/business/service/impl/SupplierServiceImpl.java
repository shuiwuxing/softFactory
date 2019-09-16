package com.yang.ess.business.service.impl;

import com.yang.ess.business.entity.Supplier;
import com.yang.ess.business.mapper.SupplierMapper;
import com.yang.ess.business.service.ISupplierService;
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
 * 供应商 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:37
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public IPage<Supplier> findSuppliers(QueryRequest request, Supplier supplier) {
        LambdaQueryWrapper<Supplier> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Supplier> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Supplier> findSuppliers(Supplier supplier) {
	    LambdaQueryWrapper<Supplier> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createSupplier(Supplier supplier) {
        this.save(supplier);
    }

    @Override
    @Transactional
    public void updateSupplier(Supplier supplier) {
        this.saveOrUpdate(supplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(Supplier supplier) {
        LambdaQueryWrapper<Supplier> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
