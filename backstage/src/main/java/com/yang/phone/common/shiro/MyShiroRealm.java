package com.yang.phone.common.shiro;
import com.yang.phone.mapper.sys.SysUserMapper;
import com.yang.phone.service.sys.SysRoleService;
import com.yang.phone.service.sys.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    public SysUserMapper sysUserMapper;

    @Autowired
    public SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;


    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        Map<String,Object> params=new HashMap<>();
        Map<String,Object> user = sysUserMapper.findUserInfo(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<Map<String,Object>> roles=sysUserService.findRole(user);
        for (Map<String,Object> role:roles) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.get("role_code").toString());
            List<Map<String,Object>> permissions= sysRoleService.findPermission(role);
            for (Map<String,Object> permission:permissions) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.get("permission_code").toString());
            }
        }
        return simpleAuthorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        Map<String,Object> user =sysUserMapper.findUserInfo(name) ;
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.get("password").toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
