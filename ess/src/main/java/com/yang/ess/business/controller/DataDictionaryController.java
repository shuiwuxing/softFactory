package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.DataDictionary;
import com.yang.ess.business.service.IDataDictionaryService;
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
 * 数据字典 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:09
 */
@Slf4j
@Validated
@Controller
public class DataDictionaryController extends BaseController {

    @Autowired
    private IDataDictionaryService dataDictionaryService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "dataDictionary")
    private String dataDictionaryIndex(){
        return FebsUtil.view("dataDictionary/dataDictionary");
    }

    @GetMapping("dataDictionary")
    @ResponseBody
    @RequiresPermissions("dataDictionary:list")
    public FebsResponse getAllDataDictionarys(DataDictionary dataDictionary) {
        return new FebsResponse().success().data(dataDictionaryService.findDataDictionarys(dataDictionary));
    }

    @GetMapping("dataDictionary/list")
    @ResponseBody
    @RequiresPermissions("dataDictionary:list")
    public FebsResponse dataDictionaryList(QueryRequest request, DataDictionary dataDictionary) {
        Map<String, Object> dataTable = getDataTable(this.dataDictionaryService.findDataDictionarys(request, dataDictionary));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增DataDictionary")
    @PostMapping("dataDictionary")
    @ResponseBody
    @RequiresPermissions("dataDictionary:add")
    public FebsResponse addDataDictionary(@Valid DataDictionary dataDictionary) throws FebsException {
        try {
            this.dataDictionaryService.createDataDictionary(dataDictionary);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增DataDictionary失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除DataDictionary")
    @GetMapping("dataDictionary/delete")
    @ResponseBody
    @RequiresPermissions("dataDictionary:delete")
    public FebsResponse deleteDataDictionary(DataDictionary dataDictionary) throws FebsException {
        try {
            this.dataDictionaryService.deleteDataDictionary(dataDictionary);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除DataDictionary失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改DataDictionary")
    @PostMapping("dataDictionary/update")
    @ResponseBody
    @RequiresPermissions("dataDictionary:update")
    public FebsResponse updateDataDictionary(DataDictionary dataDictionary) throws FebsException {
        try {
            this.dataDictionaryService.updateDataDictionary(dataDictionary);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改DataDictionary失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("dataDictionary/excel")
    @ResponseBody
    @RequiresPermissions("dataDictionary:export")
    public void export(QueryRequest queryRequest, DataDictionary dataDictionary, HttpServletResponse response) throws FebsException {
        try {
            List<DataDictionary> dataDictionarys = this.dataDictionaryService.findDataDictionarys(queryRequest, dataDictionary).getRecords();
            ExcelKit.$Export(DataDictionary.class, response).downXlsx(dataDictionarys, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
