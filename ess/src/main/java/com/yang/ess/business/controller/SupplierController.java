package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.Supplier;
import com.yang.ess.business.service.ISupplierService;
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
 * 供应商 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:37
 */
@Slf4j
@Validated
@Controller
public class SupplierController extends BaseController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "supplier")
    private String supplierIndex(){
        return FebsUtil.view("supplier/supplier");
    }

    @GetMapping("supplier")
    @ResponseBody
    @RequiresPermissions("supplier:list")
    public FebsResponse getAllSuppliers(Supplier supplier) {
        return new FebsResponse().success().data(supplierService.findSuppliers(supplier));
    }

    @GetMapping("supplier/list")
    @ResponseBody
    @RequiresPermissions("supplier:list")
    public FebsResponse supplierList(QueryRequest request, Supplier supplier) {
        Map<String, Object> dataTable = getDataTable(this.supplierService.findSuppliers(request, supplier));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Supplier")
    @PostMapping("supplier")
    @ResponseBody
    @RequiresPermissions("supplier:add")
    public FebsResponse addSupplier(@Valid Supplier supplier) throws FebsException {
        try {
            this.supplierService.createSupplier(supplier);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Supplier失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Supplier")
    @GetMapping("supplier/delete")
    @ResponseBody
    @RequiresPermissions("supplier:delete")
    public FebsResponse deleteSupplier(Supplier supplier) throws FebsException {
        try {
            this.supplierService.deleteSupplier(supplier);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Supplier失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Supplier")
    @PostMapping("supplier/update")
    @ResponseBody
    @RequiresPermissions("supplier:update")
    public FebsResponse updateSupplier(Supplier supplier) throws FebsException {
        try {
            this.supplierService.updateSupplier(supplier);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Supplier失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("supplier/excel")
    @ResponseBody
    @RequiresPermissions("supplier:export")
    public void export(QueryRequest queryRequest, Supplier supplier, HttpServletResponse response) throws FebsException {
        try {
            List<Supplier> suppliers = this.supplierService.findSuppliers(queryRequest, supplier).getRecords();
            ExcelKit.$Export(Supplier.class, response).downXlsx(suppliers, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
