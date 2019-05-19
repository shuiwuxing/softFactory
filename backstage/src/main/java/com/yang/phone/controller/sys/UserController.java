package com.yang.phone.controller.sys;
import com.github.pagehelper.PageInfo;
import com.yang.phone.common.ResultMessage;
import com.yang.phone.service.sys.SysUserService;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    public SysUserService sysUserService;

    @RequestMapping("/getuserinfo")
    @RequiresPermissions(value = "root:user_list")
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

}
