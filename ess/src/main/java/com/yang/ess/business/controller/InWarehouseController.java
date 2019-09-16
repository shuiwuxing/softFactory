package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.InWarehouse;
import com.yang.ess.business.service.IInWarehouseService;
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
 * 入库单 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:13
 */
@Slf4j
@Validated
@Controller
public class InWarehouseController extends BaseController {

    @Autowired
    private IInWarehouseService inWarehouseService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "inWarehouse")
    private String inWarehouseIndex(){
        return FebsUtil.view("inWarehouse/inWarehouse");
    }

    @GetMapping("inWarehouse")
    @ResponseBody
    @RequiresPermissions("inOrder:view")
    public FebsResponse getAllInWarehouses(InWarehouse inWarehouse) {
        return new FebsResponse().success().data(inWarehouseService.findInWarehouses(inWarehouse));
    }

    @GetMapping("inWarehouse/list")
    @ResponseBody
    @RequiresPermissions("inOrder:view")
    public FebsResponse inWarehouseList(QueryRequest request, InWarehouse inWarehouse) {
        Map<String, Object> dataTable = getDataTable(this.inWarehouseService.findInWarehouses(request, inWarehouse));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增InWarehouse")
    @PostMapping("inWarehouse/add")
    @ResponseBody
    @RequiresPermissions("inOrder:inOrderAdd")
    public FebsResponse addInWarehouse(InWarehouse inWarehouse,String details) throws FebsException {
        try {
            this.inWarehouseService.createInWarehouse(inWarehouse,details);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增InWarehouse失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除InWarehouse")
    @GetMapping("inWarehouse/delete")
    @ResponseBody
    @RequiresPermissions("inWarehouse:delete")
    public FebsResponse deleteInWarehouse(InWarehouse inWarehouse) throws FebsException {
        try {
            this.inWarehouseService.deleteInWarehouse(inWarehouse);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除InWarehouse失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改InWarehouse")
    @PostMapping("inWarehouse/update")
    @ResponseBody
    @RequiresPermissions("inWarehouse:update")
    public FebsResponse updateInWarehouse(InWarehouse inWarehouse) throws FebsException {
        try {
            this.inWarehouseService.updateInWarehouse(inWarehouse);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改InWarehouse失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("inWarehouse/excel")
    @ResponseBody
    @RequiresPermissions("inWarehouse:export")
    public void export(QueryRequest queryRequest, InWarehouse inWarehouse, HttpServletResponse response) throws FebsException {
        try {
            List<InWarehouse> inWarehouses = this.inWarehouseService.findInWarehouses(queryRequest, inWarehouse).getRecords();
            ExcelKit.$Export(InWarehouse.class, response).downXlsx(inWarehouses, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
