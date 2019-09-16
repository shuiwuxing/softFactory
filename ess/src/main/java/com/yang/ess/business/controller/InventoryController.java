package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.Inventory;
import com.yang.ess.business.service.IInventoryService;
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
 * 库存数量 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:21
 */
@Slf4j
@Validated
@Controller
public class InventoryController extends BaseController {

    @Autowired
    private IInventoryService inventoryService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "inventory")
    private String inventoryIndex(){
        return FebsUtil.view("inventory/inventory");
    }

    @GetMapping("inventory")
    @ResponseBody
    @RequiresPermissions("inventory:list")
    public FebsResponse getAllInventorys(Inventory inventory) {
        return new FebsResponse().success().data(inventoryService.findInventorys(inventory));
    }

    @GetMapping("inventory/list")
    @ResponseBody
    @RequiresPermissions("inventory:list")
    public FebsResponse inventoryList(QueryRequest request, Inventory inventory) {
        Map<String, Object> dataTable = getDataTable(this.inventoryService.findInventorys(request, inventory));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Inventory")
    @PostMapping("inventory")
    @ResponseBody
    @RequiresPermissions("inventory:add")
    public FebsResponse addInventory(@Valid Inventory inventory) throws FebsException {
        try {
            this.inventoryService.createInventory(inventory);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Inventory失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Inventory")
    @GetMapping("inventory/delete")
    @ResponseBody
    @RequiresPermissions("inventory:delete")
    public FebsResponse deleteInventory(Inventory inventory) throws FebsException {
        try {
            this.inventoryService.deleteInventory(inventory);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Inventory失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Inventory")
    @PostMapping("inventory/update")
    @ResponseBody
    @RequiresPermissions("inventory:update")
    public FebsResponse updateInventory(Inventory inventory) throws FebsException {
        try {
            this.inventoryService.updateInventory(inventory);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Inventory失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("inventory/excel")
    @ResponseBody
    @RequiresPermissions("inventory:export")
    public void export(QueryRequest queryRequest, Inventory inventory, HttpServletResponse response) throws FebsException {
        try {
            List<Inventory> inventorys = this.inventoryService.findInventorys(queryRequest, inventory).getRecords();
            ExcelKit.$Export(Inventory.class, response).downXlsx(inventorys, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
