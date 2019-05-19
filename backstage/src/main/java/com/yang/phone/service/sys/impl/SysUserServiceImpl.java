package com.yang.phone.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.phone.mapper.sys.SysUserMapper;
import com.yang.phone.mapper.sys.SysUserRoleMapper;
import com.yang.phone.service.sys.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Map<String, Object> login(Map<String, Object> params) {
        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(params.get("loginId").toString(), params.get("password").toString()));
        //return userMapper.login(params);
        Map<String,Object> result=new HashMap<>();
        result.put("code","登录成功");
        return  result;
    }

    @Override
    public Map<String, Object> findUserInfo(String uid) {
        return sysUserMapper.findUserInfo(uid);
    }

    @Override
    public  PageInfo<Map<String, Object>> findAll(Map<String, Object> params) {
        PageHelper.startPage(params);
        List<Map<String, Object>> list= sysUserMapper.findAlluser(params);
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> findRole(Map<String, Object> params) {
        return sysUserRoleMapper.findAllData(params);
    }

    @Override
    public int addUser(Map<String,Object> user) {
        return sysUserMapper.addUser(user);
    }

    @Override
    public int updateUser(Map<String,Object> user) {
        return sysUserMapper.updateUser(user);
    }

    @Override
    public int deleteUser(String uid) {
        return sysUserMapper.deleteUser(uid);
    }
}
