package com.yang.phone.mapper.sys;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysUserMapper {
    public Map<String,Object> findUserInfo(String value);
    public List<Map<String,Object>> findAlluser(Map<String, Object> params);
    public int addUser(Map<String, Object> params);
    public  int updateUser(Map<String, Object> params);
    public int deleteUser(String uid);
}
