package com.yang.phone.mapper.sys;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface SysPermissionMapper {
    public Map<String,Object> findDataById(String id);
    public List<Map<String,Object>> findAllData(Map<String, Object> params);
    public int  addData(Map<String, Object> params);
    public int  updateData(Map<String, Object> params);
    public int  deleteData(String id);
}
