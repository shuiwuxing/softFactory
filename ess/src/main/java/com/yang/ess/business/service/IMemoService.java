package com.yang.ess.business.service;

import com.yang.ess.business.entity.Memo;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 备忘录 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:25
 */
public interface IMemoService extends IService<Memo> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param memo memo
     * @return IPage<Memo>
     */
    IPage<Memo> findMemos(QueryRequest request, Memo memo);

    /**
     * 查询（所有）
     *
     * @param memo memo
     * @return List<Memo>
     */
    List<Memo> findMemos(Memo memo);

    /**
     * 新增
     *
     * @param memo memo
     */
    void createMemo(Memo memo);

    /**
     * 修改
     *
     * @param memo memo
     */
    void updateMemo(Memo memo);

    /**
     * 删除
     *
     * @param memo memo
     */
    void deleteMemo(Memo memo);
}
