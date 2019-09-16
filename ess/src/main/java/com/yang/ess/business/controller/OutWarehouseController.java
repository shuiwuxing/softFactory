package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.OutWarehouse;
import com.yang.ess.business.service.IOutWarehouseService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 出库单 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:30
 */
@Slf4j
@Validated
@Controller
public class
OutWarehouseController extends BaseController {

    @Autowired
    private IOutWarehouseService outWarehouseService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "outWarehouse")
    private String outWarehouseIndex(){
        return FebsUtil.view("outWarehouse/outWarehouse");
    }

    @GetMapping("outWarehouse")
    @ResponseBody
    @RequiresPermissions("outWarehouse:list")
    public FebsResponse getAllOutWarehouses(OutWarehouse outWarehouse) {
        return new FebsResponse().success().data(outWarehouseService.findOutWarehouses(outWarehouse));
    }

    @GetMapping("outWarehouse/list")
    @ResponseBody
    @RequiresPermissions("outOrder:view")
    public FebsResponse outWarehouseList(QueryRequest request, OutWarehouse outWarehouse) {
        Map<String, Object> dataTable = getDataTable(this.outWarehouseService.findOutWarehouses(request, outWarehouse));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增OutWarehouse")
    @PostMapping("outWarehouse/add")
    @ResponseBody
    @RequiresPermissions("outOrder:outOrderAdd")
    public FebsResponse addOutWarehouse(OutWarehouse outWarehouse, String details,String cName,String phone) throws FebsException {
        try {
            this.outWarehouseService.createOutWarehouse(outWarehouse,details,cName,phone);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增OutWarehouse失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除OutWarehouse")
    @GetMapping("outWarehouse/delete")
    @ResponseBody
    @RequiresPermissions("outWarehouse:delete")
    public FebsResponse deleteOutWarehouse(OutWarehouse outWarehouse) throws FebsException {
        try {
            this.outWarehouseService.deleteOutWarehouse(outWarehouse);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除OutWarehouse失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改OutWarehouse")
    @PostMapping("outWarehouse/update")
    @ResponseBody
    @RequiresPermissions("outWarehouse:update")
    public FebsResponse updateOutWarehouse(OutWarehouse outWarehouse) throws FebsException {
        try {
            this.outWarehouseService.updateOutWarehouse(outWarehouse);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改OutWarehouse失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("outWarehouse/excel")
    @ResponseBody
    @RequiresPermissions("outWarehouse:export")
    public void export(QueryRequest queryRequest, OutWarehouse outWarehouse, HttpServletResponse response) throws FebsException {
        try {
            List<OutWarehouse> outWarehouses = this.outWarehouseService.findOutWarehouses(queryRequest, outWarehouse).getRecords();
            ExcelKit.$Export(OutWarehouse.class, response).downXlsx(outWarehouses, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
