package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.Goods;
import com.yang.ess.business.service.IGoodsService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 商品表 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:04
 */
@Slf4j
@Validated
@Controller
public class GoodsController extends BaseController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "goods")
    private String goodsIndex(){
        return FebsUtil.view("goods/goods");
    }

    @GetMapping("goods")
    @ResponseBody
    @RequiresPermissions("goods:list")
    public FebsResponse getAllGoodss(Goods goods) {
        return new FebsResponse().success().data(goodsService.findGoodss(goods));
    }

    @GetMapping("goods/list")
    @ResponseBody
    @RequiresPermissions("goods:view")
    public FebsResponse goodsList(QueryRequest request, Goods goods) {
        Map<String, Object> dataTable = getDataTable(this.goodsService.findGoodss(request, goods));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Goods")
    @PostMapping("goods/goodsAdd")
    @ResponseBody
    @RequiresPermissions("goods:goodsAdd")
    public FebsResponse addGoods(@Valid Goods goods) throws FebsException {
        try {
            this.goodsService.createGoods(goods);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Goods失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Goods")
    @GetMapping("goods/delete")
    @ResponseBody
    @RequiresPermissions("goods:delete")
    public FebsResponse deleteGoods(Goods goods) throws FebsException {
        try {
            this.goodsService.deleteGoods(goods);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Goods失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Goods")
    @PostMapping("goods/update")
    @ResponseBody
    @RequiresPermissions("goods:update")
    public FebsResponse updateGoods(Goods goods) throws FebsException {
        try {
            this.goodsService.updateGoods(goods);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Goods失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("goods/excel")
    @ResponseBody
    @RequiresPermissions("goods:export")
    public void export(QueryRequest queryRequest, Goods goods, HttpServletResponse response) throws FebsException {
        try {
            List<Goods> goodss = this.goodsService.findGoodss(queryRequest, goods).getRecords();
            ExcelKit.$Export(Goods.class, response).downXlsx(goodss, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
