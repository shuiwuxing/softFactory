package com.yang.phone.mapper.sys;

import java.util.List;
import java.util.Map;

public interface SysRolePermissionMapper {

    public List<Map<String,Object>> findAllData(Map<String, Object> params);
    public int  addData(Map<String, Object> params);
    public int  deleteData(String id);

}
