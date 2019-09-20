package com.yang.ess.business.service.impl;

import com.yang.ess.business.entity.Goods;
import com.yang.ess.business.mapper.GoodsMapper;
import com.yang.ess.business.service.IGoodsService;
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
 * 商品表 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:04
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public IPage<Goods> findGoodss(QueryRequest request, Goods goods) {
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        if(goods.getName()!=null){
            queryWrapper.like(Goods::getName,goods.getName());
        }
        queryWrapper.orderByDesc(Goods::getId);
        Page<Goods> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Goods> findGoodss(Goods goods) {
	    LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createGoods(Goods goods) {
        this.save(goods);
    }

    @Override
    @Transactional
    public void updateGoods(Goods goods) {
        this.saveOrUpdate(goods);
    }

    @Override
    @Transactional
    public void deleteGoods(Goods goods) {
        LambdaQueryWrapper<Goods> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
