package com.yang.phone.service.sys;

import java.util.List;
import java.util.Map;

/**
 * 权限管理
 * @author JIANHUI YANG
 * @date 2019年05月02月
 *
 * **/
public interface SysPermissionService {

    public Map<String,Object> findDataById(String id);

    public List<Map<String,Object>> findAllData(Map<String, Object> params);

    public int  addData(Map<String, Object> params);

    public int  updateData(Map<String, Object> params);

    public int  deleteData(String id);

}
