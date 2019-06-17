package com.yang.phone.controller.sys;


import com.yang.phone.common.ResultMessage;
import com.yang.phone.common.sysenum.CodeInfoEnum;
import com.yang.phone.service.sys.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
public class ShiroController {

    @Autowired
    SysUserService sysUserService;

    /**
     * 登录方法
     * @param
     * @return
     */
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage ajaxLogin(@RequestBody Map<String,Object> params) {
        Map<String,Object> result;
        try {
            result= sysUserService.login(params);
            return new ResultMessage(result);
        } catch (IncorrectCredentialsException e) {
            return new ResultMessage(2001, CodeInfoEnum.getPaymentType(2001).getMessage(),null);
        } catch (LockedAccountException e) {
            return new ResultMessage(2002, CodeInfoEnum.getPaymentType(2002).getMessage(),null);
        } catch (AuthenticationException e) {
            return new ResultMessage(2003, CodeInfoEnum.getPaymentType(2003).getMessage(),null);
        } catch (Exception e) {
           return  new ResultMessage(2004, CodeInfoEnum.getPaymentType(2004).getMessage(),null);
        }
    }
    @RequestMapping(value = "/getToken")
    @ResponseBody
    public Object getToken() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }
    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauthorized")
    @ResponseBody
    public Object unauthorized(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未授权");
        return map;
    }


}
