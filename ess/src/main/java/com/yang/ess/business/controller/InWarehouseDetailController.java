package com.yang.ess.business.controller;

import com.yang.ess.business.entity.Goods;
import com.yang.ess.business.service.IGoodsService;
import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.InWarehouseDetail;
import com.yang.ess.business.service.IInWarehouseDetailService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 入库单详情 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:17
 */
@Slf4j
@Validated
@Controller
public class InWarehouseDetailController extends BaseController {

    @Autowired
    private IInWarehouseDetailService inWarehouseDetailService;
    @Autowired
    IGoodsService goodsService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "inWarehouseDetail")
    private String inWarehouseDetailIndex(){
        return FebsUtil.view("inWarehouseDetail/inWarehouseDetail");
    }

    @GetMapping("inWarehouseDetail")
    @ResponseBody
    @RequiresPermissions("inWarehouseDetail:list")
    public FebsResponse getAllInWarehouseDetails(InWarehouseDetail inWarehouseDetail) {
        return new FebsResponse().success().data(inWarehouseDetailService.findInWarehouseDetails(inWarehouseDetail));
    }

    @PostMapping("inWarehouseDetail/inWarehouseDetailByImei")
    @ResponseBody
    @RequiresAuthentication
    public FebsResponse getInWarehouseDetailByImei(InWarehouseDetail inWarehouseDetail) {
        List<InWarehouseDetail> list= inWarehouseDetailService.findInWarehouseDetails(inWarehouseDetail);
        Map<String,String> result=new HashMap<>();
        if(list.size()>0){
            result.put("money",list.get(0).getMoney().toString());
            Goods goods= goodsService.getById(list.get(0).getGid());
            result.put("gName",goods.getName());
            result.put("gid",goods.getId().toString());
        }else {
            result.put("money","");
            result.put("gName","");
        }
        return new FebsResponse().success().data(result);
    }

    @GetMapping("inWarehouseDetail/list")
    @ResponseBody
    @RequiresPermissions("inWareHouse:view")
    public FebsResponse inWarehouseDetailList(QueryRequest request, InWarehouseDetail inWarehouseDetail) {
        Map<String, Object> dataTable = getDataTable(this.inWarehouseDetailService.findInWarehouseDetailsIn(request, inWarehouseDetail));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增InWarehouseDetail")
    @PostMapping("inWarehouseDetail")
    @ResponseBody
    @RequiresPermissions("inWarehouseDetail:add")
    public FebsResponse addInWarehouseDetail(@Valid InWarehouseDetail inWarehouseDetail) throws FebsException {
        try {
            this.inWarehouseDetailService.createInWarehouseDetail(inWarehouseDetail);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增InWarehouseDetail失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除InWarehouseDetail")
    @GetMapping("inWarehouseDetail/delete")
    @ResponseBody
    @RequiresPermissions("inWarehouseDetail:delete")
    public FebsResponse deleteInWarehouseDetail(InWarehouseDetail inWarehouseDetail) throws FebsException {
        try {
            this.inWarehouseDetailService.deleteInWarehouseDetail(inWarehouseDetail);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除InWarehouseDetail失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改InWarehouseDetail")
    @PostMapping("inWarehouseDetail/update")
    @ResponseBody
    @RequiresPermissions("inWareHouse:inWareHuseDetail")
    public FebsResponse updateInWarehouseDetail(InWarehouseDetail inWarehouseDetail) throws FebsException {
        try {
            this.inWarehouseDetailService.updateInWarehouseDetail(inWarehouseDetail);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改InWarehouseDetail失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("inWarehouseDetail/excel")
    @ResponseBody
    @RequiresPermissions("inWarehouseDetail:export")
    public void export(QueryRequest queryRequest, InWarehouseDetail inWarehouseDetail, HttpServletResponse response) throws FebsException {
        try {
            List<InWarehouseDetail> inWarehouseDetails = this.inWarehouseDetailService.findInWarehouseDetails(queryRequest, inWarehouseDetail).getRecords();
            ExcelKit.$Export(InWarehouseDetail.class, response).downXlsx(inWarehouseDetails, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
