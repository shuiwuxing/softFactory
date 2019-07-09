package cc.mrbird.febs.monitor.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.exception.RedisConnectException;
import cc.mrbird.febs.monitor.service.IRedisService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @author MrBird
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    private static final String INTEGER_PREFIX = "(integer) ";

    @Autowired
    private IRedisService redisService;

    @RequestMapping("keysSize")
    @RequiresPermissions("redis:view")
    public Map<String, Object> getKeysSize() throws RedisConnectException {
        return redisService.getKeysSize();
    }

    @RequestMapping("memoryInfo")
    @RequiresPermissions("redis:view")
    public Map<String, Object> getMemoryInfo() throws RedisConnectException {
        return redisService.getMemoryInfo();
    }

    @Log("执行Redis keys命令")
    @GetMapping("keys")
    public FebsResponse keys(String arg) throws RedisConnectException {
        Set<String> set = this.redisService.getKeys(arg);
        return new FebsResponse().success().data(set);
    }

    @Log("执行Redis get命令")
    @GetMapping("get")
    public FebsResponse get(String arg) throws RedisConnectException {
        String result = this.redisService.get(arg);
        return new FebsResponse().success().data(result == null ? "" : result);
    }

    @Log("执行Redis set命令")
    @GetMapping("set")
    public FebsResponse set(String arg) throws RedisConnectException {
        String[] args = arg.split(",");
        if (args.length == 1)
            return new FebsResponse().fail().data("(error) ERR wrong number of arguments for 'set' command");
        else if (args.length != 2)
            return new FebsResponse().fail().data("(error) ERR syntax error");
        String result = this.redisService.set(args[0], args[1]);
        return new FebsResponse().success().data(result);
    }

    @Log("执行Redis del命令")
    @GetMapping("del")
    public FebsResponse del(String arg) throws RedisConnectException {
        String[] args = arg.split(",");
        Long result = this.redisService.del(args);
        return new FebsResponse().success().data(INTEGER_PREFIX + result);
    }

    @Log("执行Redis exists命令")
    @GetMapping("exists")
    public FebsResponse exists(String arg) throws RedisConnectException {
        int count = 0;
        String[] arr = arg.split(",");
        for (String key : arr) {
            if (this.redisService.exists(key))
                count++;
        }
        return new FebsResponse().success().data(INTEGER_PREFIX + count);
    }

    @Log("执行Redis pttl命令")
    @GetMapping("pttl")
    public FebsResponse pttl(String arg) throws RedisConnectException {
        return new FebsResponse().success().data(INTEGER_PREFIX + this.redisService.pttl(arg));
    }

    @Log("执行Redis pexpire命令")
    @GetMapping("pexpire")
    public FebsResponse pexpire(String arg) throws RedisConnectException {
        String[] arr = arg.split(",");
        if (arr.length != 2 || !isValidLong(arr[1])) {
            return new FebsResponse().fail().data("(error) ERR wrong number of arguments for 'pexpire' command");
        }
        return new FebsResponse().success().data(INTEGER_PREFIX + this.redisService.pexpire(arr[0], Long.valueOf(arr[1])));
    }

    private static boolean isValidLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
