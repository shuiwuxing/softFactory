package com.yang.ess.business.service.impl;

import com.yang.ess.business.entity.Memo;
import com.yang.ess.business.mapper.MemoMapper;
import com.yang.ess.business.service.IMemoService;
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
 * 备忘录 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:25
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MemoServiceImpl extends ServiceImpl<MemoMapper, Memo> implements IMemoService {

    @Autowired
    private MemoMapper memoMapper;

    @Override
    public IPage<Memo> findMemos(QueryRequest request, Memo memo) {
        LambdaQueryWrapper<Memo> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Memo> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Memo> findMemos(Memo memo) {
	    LambdaQueryWrapper<Memo> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createMemo(Memo memo) {
        this.save(memo);
    }

    @Override
    @Transactional
    public void updateMemo(Memo memo) {
        this.saveOrUpdate(memo);
    }

    @Override
    @Transactional
    public void deleteMemo(Memo memo) {
        LambdaQueryWrapper<Memo> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
