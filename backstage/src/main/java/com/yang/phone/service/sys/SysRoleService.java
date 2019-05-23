package com.yang.phone.service.sys;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * @author JIANHUI YANG
 * @date 2019年05月02月
 *
 * **/
public interface SysRoleService {

    public Map<String,Object> findDataById(String id);

    public PageInfo<Map<String,Object>> findAllData(Map<String, Object> params);

    public List<Map<String,Object>> findPermission(Map<String, Object> params);

    public int  addData(Map<String, Object> params);

    public int  updateData(Map<String, Object> params);

    public int  deleteData(String id);
}
