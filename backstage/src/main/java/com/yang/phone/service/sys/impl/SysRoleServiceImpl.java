package com.yang.phone.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.phone.mapper.sys.SysRoleMapper;
import com.yang.phone.mapper.sys.SysRolePermissionMapper;
import com.yang.phone.service.sys.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    public SysRoleMapper sysRoleMapper;

    @Autowired
    public SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public Map<String, Object> findDataById(String id) {
        return sysRoleMapper.findDataById(id);
    }

    @Override
    public PageInfo<Map<String, Object>> findAllData(Map<String, Object> params) {
        PageHelper.startPage(params);
        List<Map<String,Object>> list= sysRoleMapper.findAllData(params);
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> findPermission(Map<String, Object> params) {
        return sysRolePermissionMapper.findAllData(params);
    }

    @Override
    public int addData(Map<String, Object> params) {
        return sysRoleMapper.addData(params);
    }

    @Override
    public int updateData(Map<String, Object> params) {
        return sysRoleMapper.updateData(params);
    }

    @Override
    public int deleteData(String id) {
        return sysRoleMapper.deleteData(id);
    }
}
