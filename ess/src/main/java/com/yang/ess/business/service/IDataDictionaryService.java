package com.yang.ess.business.service;

import com.yang.ess.business.entity.DataDictionary;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 数据字典 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:09
 */
public interface IDataDictionaryService extends IService<DataDictionary> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param dataDictionary dataDictionary
     * @return IPage<DataDictionary>
     */
    IPage<DataDictionary> findDataDictionarys(QueryRequest request, DataDictionary dataDictionary);

    /**
     * 查询（所有）
     *
     * @param dataDictionary dataDictionary
     * @return List<DataDictionary>
     */
    List<DataDictionary> findDataDictionarys(DataDictionary dataDictionary);

    /**
     * 新增
     *
     * @param dataDictionary dataDictionary
     */
    void createDataDictionary(DataDictionary dataDictionary);

    /**
     * 修改
     *
     * @param dataDictionary dataDictionary
     */
    void updateDataDictionary(DataDictionary dataDictionary);

    /**
     * 删除
     *
     * @param dataDictionary dataDictionary
     */
    void deleteDataDictionary(DataDictionary dataDictionary);
}
