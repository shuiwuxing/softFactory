package com.yang.ess.business.controller;

import com.alibaba.fastjson.JSON;
import com.yang.ess.business.entity.*;
import com.yang.ess.business.service.*;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.utils.FebsUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * 路由页面跳转
 *
 * */
@Controller("businessView")
public class ViewController {

    @Autowired
    IOutWarehouseService outWarehouseService;
    @Autowired
    IInWarehouseService inWarehouseService;
    @Autowired
    IOutWarehouseDetailService outWarehouseDetailService;
    @Autowired
    IInWarehouseDetailService inWarehouseDetailService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    IGoodsService goodsService;

    //入库单界面
    @GetMapping(FebsConstant.VIEW_PREFIX + "business/order/inOrder")
    @RequiresPermissions("inOrder:view")
    public String inOrder() {
        return FebsUtil.view("business/order/inOrder");
    }
    @GetMapping(FebsConstant.VIEW_PREFIX + "business/order/inOrderAdd")
    @RequiresPermissions("inOrder:inOrderAdd")
    public String inOrderAdd() {
        return FebsUtil.view("business/order/inOrderAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "business/inOrder/detail/{id}")
    @RequiresPermissions("inOrder:detail")
    public String inWareHouseDetailDetail (@PathVariable Integer id, Model model) {
        InWarehouse inWarehouse = inWarehouseService.getById(id);
        List<Map<String,Object>> list= inWarehouseDetailService.selectInWarehouseDetail(id.toString());
        model.addAttribute("data", inWarehouse);
        model.addAttribute("list", list);
        return FebsUtil.view("business/order/inOrderDetail");
    }

    //出库单
    @GetMapping(FebsConstant.VIEW_PREFIX + "business/order/outOrder")
    @RequiresPermissions("outOrder:view")
    public String outOrder() {
        return FebsUtil.view("business/order/outOrder");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "business/order/outOrderAdd")
    @RequiresPermissions("outOrder:outOrderAdd")
    public String outOrderAdd() {
        return FebsUtil.view("business/order/outOrderAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "business/outOrder/detail/{id}")
    @RequiresPermissions("outOrder:detail")
    public String outWarehouseDetail(@PathVariable Integer id, Model model) {
        OutWarehouse outWarehouse = outWarehouseService.getById(id);
        List<Map<String,Object>> list= outWarehouseDetailService.selectOutWarehouseDetail(id.toString());
        Customer customer= customerService.getById(outWarehouse.getCid());
        model.addAttribute("data", outWarehouse);
        model.addAttribute("customer", customer==null?new Customer():customer);
        model.addAttribute("list", list);
        return FebsUtil.view("business/order/outOrderDetail");
    }

    //商品管理
    @GetMapping(FebsConstant.VIEW_PREFIX + "business/goods/goods")
    @RequiresPermissions("goods:view")
    public String goods() {
        return FebsUtil.view("business/goods/goods");
    }
    @GetMapping(FebsConstant.VIEW_PREFIX + "business/goods/goodsAdd")
    @RequiresPermissions("goods:goodsAdd")
    public String goodsAdd() {
        return FebsUtil.view("business/goods/goodsAdd");
    }

    //手机盘点
    @GetMapping(FebsConstant.VIEW_PREFIX + "business/inWareHouse/inWareHouse")
    @RequiresPermissions("inWareHouse:view")
    public String inWareHouse() {
        return FebsUtil.view("business/inwarehouse/inWareHouse");
    }
    @GetMapping(FebsConstant.VIEW_PREFIX + "business/inWareHouse/inWareHouseDetail/{id}")
    @RequiresPermissions("inWareHouse:inWareHuseDetail")
    public String inWareHouseDetail(@PathVariable Integer id, Model model) {
         InWarehouseDetail inWarehouseDetail= inWarehouseDetailService.getById(id);
         Goods goods=goodsService.getById(inWarehouseDetail.getGid());
         model.addAttribute("data", inWarehouseDetail);
         model.addAttribute("goods", goods);
        return FebsUtil.view("business/inwarehouse/inWareHouseDetail");
    }


}
