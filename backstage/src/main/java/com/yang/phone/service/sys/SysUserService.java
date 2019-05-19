package com.yang.phone.service.sys;

import com.github.pagehelper.PageInfo;
import com.yang.phone.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 用户业务逻辑
 * @author JIANHUI YANG
 * @date 2019年05月02月
 *
 * **/
public interface SysUserService {

    public Map<String,Object> login(Map<String, Object> params);

    public Map<String, Object> findUserInfo(String uid);

    public PageInfo<Map<String, Object>> findAll(Map<String,Object> params);

    public List<Map<String, Object>> findRole(Map<String,Object> params);

    public int addUser(Map<String,Object> user);

    public int updateUser(Map<String,Object> user);

    public int deleteUser(String uid);
}
