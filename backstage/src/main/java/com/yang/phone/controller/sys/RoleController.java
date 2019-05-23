package com.yang.phone.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yang.phone.common.ResultMessage;
import com.yang.phone.service.sys.SysPermissionService;
import com.yang.phone.service.sys.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    public SysRoleService sysRoleService;

    @RequestMapping("/getRole")
    @ResponseBody
    public ResultMessage getRole(@RequestBody Map<String,Object> params) {
        String uid=params.get("uid").toString();
        Map<String,Object> permission= sysRoleService.findDataById(uid);
        return new ResultMessage(permission);
    }
    @RequestMapping("/roleList")
    @RequiresGuest
    @ResponseBody
    public ResultMessage roleList(@RequestBody Map<String,Object> params) {
        PageInfo<Map<String,Object>> permissions= sysRoleService.findAllData(params);
        return new ResultMessage(permissions);
    }
    @RequestMapping("/addPermission")
    @ResponseBody
    public ResultMessage addPermission(@RequestBody Map<String,Object> params) {
        sysRoleService.addData(params);
        return new ResultMessage();
    }
    @RequestMapping("/updateRole")
    @ResponseBody
    public ResultMessage updateRole(@RequestBody Map<String,Object> params) {
        sysRoleService.updateData(params);
        return new ResultMessage();
    }
    @RequestMapping("/deleteRole")
    @ResponseBody
    public ResultMessage deleteRole(@RequestBody Map<String,Object> params) {
        String uid=params.get("uid").toString();
        sysRoleService.deleteData(uid);
        return new ResultMessage();
    }
}
