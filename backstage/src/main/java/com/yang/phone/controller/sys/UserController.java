package com.yang.phone.controller.sys;
import com.github.pagehelper.PageInfo;
import com.yang.phone.common.ResultMessage;
import com.yang.phone.service.sys.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
/*
* 用户管理
* @author Yang JianHui
* @date 2019年05月26日
* **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    public SysUserService sysUserService;

    @RequestMapping("/getinfo")
    @ResponseBody
    public ResultMessage getUserInfo(@RequestBody Map<String,Object> params) {
        String uid=params.get("uid").toString();
       Map<String,Object> user= sysUserService.findUserInfo(uid);
        return new ResultMessage(user);
    }
    @RequestMapping("/list")
    @RequiresUser
    @ResponseBody
    public ResultMessage userList(@RequestBody Map<String,Object> params) {
        PageInfo<Map<String,Object>> user= sysUserService.findAll(params);
        return new ResultMessage(user);
    }
    @RequestMapping("/add")
    @ResponseBody
    public ResultMessage addUser(@RequestBody Map<String,Object> params) {
        sysUserService.addUser(params);
        return new ResultMessage();
    }
    @RequestMapping("/update")
    @ResponseBody
    public ResultMessage updateUser(@RequestBody Map<String,Object> params) {
        sysUserService.updateUser(params);
        return new ResultMessage();
    }
    @RequestMapping("/delete")
    @ResponseBody
    public ResultMessage deleteUser(@RequestBody Map<String,Object> params) {
        String uid=params.get("uid").toString();
        sysUserService.deleteUser(uid);
        return new ResultMessage();
    }
}
