package com.yang.ess.business.mapper;

import com.yang.ess.business.entity.OutWarehouseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 出库单详情 Mapper
 *
 * @author MrBird
 * @date 2019-09-04 22:52:33
 */
public interface OutWarehouseDetailMapper extends BaseMapper<OutWarehouseDetail> {

    List<Map<String,Object>> selectOutWarehouseDetail(@Param("param") Map<String,Object> param);
}
