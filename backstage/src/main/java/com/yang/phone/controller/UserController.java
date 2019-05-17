package com.yang.phone.controller;
import com.github.pagehelper.PageInfo;
import com.yang.phone.common.ResultMessage;
import com.yang.phone.service.sys.SysUserService;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("user")
public class UserController {
    @Autowired
    public SysUserService sysUserService;

    @RequestMapping("/getuserinfo")
    @ResponseBody
    public ResultMessage getUserInfo(@RequestBody Map<String,Object> params) {
        String uid=params.get("uid").toString();
       Map<String,Object> user= sysUserService.findUserInfo(uid);
        return new ResultMessage(user);
    }
    @RequestMapping("/userList")
    @RequiresGuest
    @ResponseBody
    public ResultMessage userList(@RequestBody Map<String,Object> params) {
        PageInfo<Map<String,Object>> user= sysUserService.findAll(params);
        return new ResultMessage(user);
    }
    @RequestMapping("/addUser")
    @ResponseBody
    public ResultMessage addUser(@RequestBody Map<String,Object> params) {
        sysUserService.addUser(params);
        return new ResultMessage();
    }
    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultMessage updateUser(@RequestBody Map<String,Object> params) {
        sysUserService.updateUser(params);
        return new ResultMessage();
    }
    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResultMessage deleteUser(@RequestBody Map<String,Object> params) {
        String uid=params.get("uid").toString();
        sysUserService.deleteUser(uid);
        return new ResultMessage();
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResultMessage login(@RequestBody Map<String,Object> params) {
        Map<String,Object> user= sysUserService.login(params);
        return new ResultMessage(user);
    }
}
