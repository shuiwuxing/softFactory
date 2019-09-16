package com.yang.ess.business.service;

import com.yang.ess.business.entity.Goods;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品表 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:04
 */
public interface IGoodsService extends IService<Goods> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param goods goods
     * @return IPage<Goods>
     */
    IPage<Goods> findGoodss(QueryRequest request, Goods goods);

    /**
     * 查询（所有）
     *
     * @param goods goods
     * @return List<Goods>
     */
    List<Goods> findGoodss(Goods goods);

    /**
     * 新增
     *
     * @param goods goods
     */
    void createGoods(Goods goods);

    /**
     * 修改
     *
     * @param goods goods
     */
    void updateGoods(Goods goods);

    /**
     * 删除
     *
     * @param goods goods
     */
    void deleteGoods(Goods goods);
}
