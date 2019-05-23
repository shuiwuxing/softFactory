package com.yang.phone.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yang.phone.common.ResultMessage;
import com.yang.phone.service.sys.SysPermissionService;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    public SysPermissionService sysPermissionService;

    @RequestMapping("/getPermission")
    @ResponseBody
    public ResultMessage getPermission(@RequestBody Map<String,Object> params) {
        String uid=params.get("uid").toString();
        Map<String,Object> permission= sysPermissionService.findDataById(uid);
        return new ResultMessage(permission);
    }
    @RequestMapping("/permissionList")
    @RequiresGuest
    @ResponseBody
    public ResultMessage permissionList(@RequestBody Map<String,Object> params) {
        PageInfo<Map<String,Object>> permissions= sysPermissionService.findAllData(params);
        return new ResultMessage(permissions);
    }
    @RequestMapping("/addPermission")
    @ResponseBody
    public ResultMessage addPermission(@RequestBody Map<String,Object> params) {
        sysPermissionService.addData(params);
        return new ResultMessage();
    }
    @RequestMapping("/updatePermission")
    @ResponseBody
    public ResultMessage updatePermission(@RequestBody Map<String,Object> params) {
        sysPermissionService.updateData(params);
        return new ResultMessage();
    }
    @RequestMapping("/deletePermission")
    @ResponseBody
    public ResultMessage deletePermission(@RequestBody Map<String,Object> params) {
        String uid=params.get("uid").toString();
        sysPermissionService.deleteData(uid);
        return new ResultMessage();
    }
}
