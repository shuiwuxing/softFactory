package com.yang.ess.business.service.impl;

import com.yang.ess.business.entity.DataDictionary;
import com.yang.ess.business.mapper.DataDictionaryMapper;
import com.yang.ess.business.service.IDataDictionaryService;
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
 * 数据字典 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:09
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DataDictionaryServiceImpl extends ServiceImpl<DataDictionaryMapper, DataDictionary> implements IDataDictionaryService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public IPage<DataDictionary> findDataDictionarys(QueryRequest request, DataDictionary dataDictionary) {
        LambdaQueryWrapper<DataDictionary> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<DataDictionary> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<DataDictionary> findDataDictionarys(DataDictionary dataDictionary) {
	    LambdaQueryWrapper<DataDictionary> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createDataDictionary(DataDictionary dataDictionary) {
        this.save(dataDictionary);
    }

    @Override
    @Transactional
    public void updateDataDictionary(DataDictionary dataDictionary) {
        this.saveOrUpdate(dataDictionary);
    }

    @Override
    @Transactional
    public void deleteDataDictionary(DataDictionary dataDictionary) {
        LambdaQueryWrapper<DataDictionary> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
