package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.OutWarehouseDetail;
import com.yang.ess.business.service.IOutWarehouseDetailService;
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
 * 出库单详情 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:33
 */
@Slf4j
@Validated
@Controller
public class OutWarehouseDetailController extends BaseController {

    @Autowired
    private IOutWarehouseDetailService outWarehouseDetailService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "outWarehouseDetail")
    private String outWarehouseDetailIndex(){
        return FebsUtil.view("outWarehouseDetail/outWarehouseDetail");
    }

    @GetMapping("outWarehouseDetail")
    @ResponseBody
    @RequiresPermissions("outWarehouseDetail:list")
    public FebsResponse getAllOutWarehouseDetails(OutWarehouseDetail outWarehouseDetail) {
        return new FebsResponse().success().data(outWarehouseDetailService.findOutWarehouseDetails(outWarehouseDetail));
    }

    @GetMapping("outWarehouseDetail/list")
    @ResponseBody
    @RequiresPermissions("outWarehouseDetail:list")
    public FebsResponse outWarehouseDetailList(QueryRequest request, OutWarehouseDetail outWarehouseDetail) {
        Map<String, Object> dataTable = getDataTable(this.outWarehouseDetailService.findOutWarehouseDetails(request, outWarehouseDetail));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增OutWarehouseDetail")
    @PostMapping("outWarehouseDetail")
    @ResponseBody
    @RequiresPermissions("outWarehouseDetail:add")
    public FebsResponse addOutWarehouseDetail(@Valid OutWarehouseDetail outWarehouseDetail) throws FebsException {
        try {
            this.outWarehouseDetailService.createOutWarehouseDetail(outWarehouseDetail);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增OutWarehouseDetail失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除OutWarehouseDetail")
    @GetMapping("outWarehouseDetail/delete")
    @ResponseBody
    @RequiresPermissions("outWarehouseDetail:delete")
    public FebsResponse deleteOutWarehouseDetail(OutWarehouseDetail outWarehouseDetail) throws FebsException {
        try {
            this.outWarehouseDetailService.deleteOutWarehouseDetail(outWarehouseDetail);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除OutWarehouseDetail失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改OutWarehouseDetail")
    @PostMapping("outWarehouseDetail/update")
    @ResponseBody
    @RequiresPermissions("outWarehouseDetail:update")
    public FebsResponse updateOutWarehouseDetail(OutWarehouseDetail outWarehouseDetail) throws FebsException {
        try {
            this.outWarehouseDetailService.updateOutWarehouseDetail(outWarehouseDetail);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改OutWarehouseDetail失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("outWarehouseDetail/excel")
    @ResponseBody
    @RequiresPermissions("outWarehouseDetail:export")
    public void export(QueryRequest queryRequest, OutWarehouseDetail outWarehouseDetail, HttpServletResponse response) throws FebsException {
        try {
            List<OutWarehouseDetail> outWarehouseDetails = this.outWarehouseDetailService.findOutWarehouseDetails(queryRequest, outWarehouseDetail).getRecords();
            ExcelKit.$Export(OutWarehouseDetail.class, response).downXlsx(outWarehouseDetails, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
