package com.yang.ess.business.service.impl;

import com.yang.ess.business.entity.Inventory;
import com.yang.ess.business.mapper.InventoryMapper;
import com.yang.ess.business.service.IInventoryService;
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
 * 库存数量 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:21
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public IPage<Inventory> findInventorys(QueryRequest request, Inventory inventory) {
        LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Inventory> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Inventory> findInventorys(Inventory inventory) {
	    LambdaQueryWrapper<Inventory> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createInventory(Inventory inventory) {
        this.save(inventory);
    }

    @Override
    @Transactional
    public void updateInventory(Inventory inventory) {
        this.saveOrUpdate(inventory);
    }

    @Override
    @Transactional
    public void deleteInventory(Inventory inventory) {
        LambdaQueryWrapper<Inventory> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
