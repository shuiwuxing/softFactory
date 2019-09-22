package com.yang.ess.business.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang.ess.business.entity.InWarehouseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 入库单详情 Mapper
 *
 * @author MrBird
 * @date 2019-09-04 22:52:17
 */
public interface InWarehouseDetailMapper extends BaseMapper<InWarehouseDetail> {

    IPage<Map<String,Object>> findInWareHouse(Page page, @Param("param") Map<String,Object> param);

    List<Map<String,Object>> selectInWarehouseDetail(@Param("param") Map<String,Object> param);

}
