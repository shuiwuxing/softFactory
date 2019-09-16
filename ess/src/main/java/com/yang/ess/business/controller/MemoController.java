package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.Memo;
import com.yang.ess.business.service.IMemoService;
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
 * 备忘录 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:25
 */
@Slf4j
@Validated
@Controller
public class MemoController extends BaseController {

    @Autowired
    private IMemoService memoService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "memo")
    private String memoIndex(){
        return FebsUtil.view("memo/memo");
    }

    @GetMapping("memo")
    @ResponseBody
    @RequiresPermissions("memo:list")
    public FebsResponse getAllMemos(Memo memo) {
        return new FebsResponse().success().data(memoService.findMemos(memo));
    }

    @GetMapping("memo/list")
    @ResponseBody
    @RequiresPermissions("memo:list")
    public FebsResponse memoList(QueryRequest request, Memo memo) {
        Map<String, Object> dataTable = getDataTable(this.memoService.findMemos(request, memo));
        return new FebsResponse().success().data(dataTable);
    }

    @Log("新增Memo")
    @PostMapping("memo")
    @ResponseBody
    @RequiresPermissions("memo:add")
    public FebsResponse addMemo(@Valid Memo memo) throws FebsException {
        try {
            this.memoService.createMemo(memo);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Memo失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Memo")
    @GetMapping("memo/delete")
    @ResponseBody
    @RequiresPermissions("memo:delete")
    public FebsResponse deleteMemo(Memo memo) throws FebsException {
        try {
            this.memoService.deleteMemo(memo);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Memo失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Memo")
    @PostMapping("memo/update")
    @ResponseBody
    @RequiresPermissions("memo:update")
    public FebsResponse updateMemo(Memo memo) throws FebsException {
        try {
            this.memoService.updateMemo(memo);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Memo失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("memo/excel")
    @ResponseBody
    @RequiresPermissions("memo:export")
    public void export(QueryRequest queryRequest, Memo memo, HttpServletResponse response) throws FebsException {
        try {
            List<Memo> memos = this.memoService.findMemos(queryRequest, memo).getRecords();
            ExcelKit.$Export(Memo.class, response).downXlsx(memos, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
