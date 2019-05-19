package com.yang.phone.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.phone.mapper.sys.SysPermissionMapper;
import com.yang.phone.mapper.sys.SysRolePermissionMapper;
import com.yang.phone.service.sys.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    public SysPermissionMapper sysPermissionMapper;

    @Autowired
    public SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public Map<String, Object> findDataById(String id) {
        return sysPermissionMapper.findDataById(id);
    }

    @Override
    public PageInfo<Map<String, Object>> findAllData(Map<String, Object> params) {
        PageHelper.startPage(params);
        List<Map<String,Object>> list= sysPermissionMapper.findAllData(params);
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
        return page;
    }

    @Override
    public int addData(Map<String, Object> params) {
        return sysPermissionMapper.addData(params);
    }

    @Override
    public int updateData(Map<String, Object> params) {
        return sysPermissionMapper.updateData(params);
    }

    @Override
    public int deleteData(String id) {
        return sysPermissionMapper.deleteData(id);
    }
}
