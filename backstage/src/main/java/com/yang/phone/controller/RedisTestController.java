package com.yang.phone.controller;
import com.yang.phone.common.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
@Controller
public class RedisTestController {
    @RequestMapping("/adddate")
    @ResponseBody
    public String addDate(String name,String value) {
        Jedis jedis=RedisUtil.getJedis();
        jedis.set(name,value);
        return "操作成功";
    }

    @RequestMapping("/getdate")
    @ResponseBody
    public String getDate(String name) {
        Jedis jedis=RedisUtil.getJedis();
        return jedis.get(name);
    }
}
