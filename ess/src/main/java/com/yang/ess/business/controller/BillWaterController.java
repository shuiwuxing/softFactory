package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.BillWater;
import com.yang.ess.business.service.IBillWaterService;
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
 * 流水记录 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:51:56
 */
@Slf4j
@Validated
@Controller
public class BillWaterController extends BaseController {

    @Autowired
    private IBillWaterService billWaterService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "billWater")
    private String billWaterIndex(){
        return FebsUtil.view("billWater/billWater");
    }

    @GetMapping("billWater")
    @ResponseBody
    @RequiresPermissions("billWater:list")
    public FebsResponse getAllBillWaters(BillWater billWater) {
        return new FebsResponse().success().data(billWaterService.findBillWaters(billWater));
    }

    @GetMapping("billWater/list")
    @ResponseBody
    @RequiresPermissions("billWater:list")
    public FebsResponse billWaterList(QueryRequest request, BillWater billWater) {
        Map<String, Object> dataTable = getDataTable(this.billWaterService.findBillWaters(request, billWater));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增BillWater")
    @PostMapping("billWater")
    @ResponseBody
    @RequiresPermissions("billWater:add")
    public FebsResponse addBillWater(@Valid BillWater billWater) throws FebsException {
        try {
            this.billWaterService.createBillWater(billWater);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增BillWater失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除BillWater")
    @GetMapping("billWater/delete")
    @ResponseBody
    @RequiresPermissions("billWater:delete")
    public FebsResponse deleteBillWater(BillWater billWater) throws FebsException {
        try {
            this.billWaterService.deleteBillWater(billWater);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除BillWater失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改BillWater")
    @PostMapping("billWater/update")
    @ResponseBody
    @RequiresPermissions("billWater:update")
    public FebsResponse updateBillWater(BillWater billWater) throws FebsException {
        try {
            this.billWaterService.updateBillWater(billWater);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改BillWater失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("billWater/excel")
    @ResponseBody
    @RequiresPermissions("billWater:export")
    public void export(QueryRequest queryRequest, BillWater billWater, HttpServletResponse response) throws FebsException {
        try {
            List<BillWater> billWaters = this.billWaterService.findBillWaters(queryRequest, billWater).getRecords();
            ExcelKit.$Export(BillWater.class, response).downXlsx(billWaters, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
