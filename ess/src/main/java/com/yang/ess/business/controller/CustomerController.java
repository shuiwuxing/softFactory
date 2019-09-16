package com.yang.ess.business.controller;

import com.yang.ess.common.annotation.Log;
import com.yang.ess.common.utils.FebsUtil;
import com.yang.ess.common.entity.FebsConstant;
import com.yang.ess.common.controller.BaseController;
import com.yang.ess.common.entity.FebsResponse;
import com.yang.ess.common.entity.QueryRequest;
import com.yang.ess.common.exception.FebsException;
import com.yang.ess.business.entity.Customer;
import com.yang.ess.business.service.ICustomerService;
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
import java.util.List;
import java.util.Map;

/**
 * 客户 Controller
 *
 * @author MrBird
 * @date 2019-09-04 22:52:00
 */
@Slf4j
@Validated
@Controller
public class CustomerController extends BaseController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "customer")
    private String customerIndex(){
        return FebsUtil.view("customer/customer");
    }

    @GetMapping("customer")
    @ResponseBody
    @RequiresPermissions("customer:list")
    public FebsResponse getAllCustomers(Customer customer) {
        return new FebsResponse().success().data(customerService.findCustomers(customer));
    }

    @GetMapping("customer/list")
    @ResponseBody
    @RequiresPermissions("customer:list")
    public FebsResponse customerList(QueryRequest request, Customer customer) {
        Map<String, Object> dataTable = getDataTable(this.customerService.findCustomers(request, customer));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping("customer/customerByPhone")
    @ResponseBody
    @RequiresAuthentication
    public FebsResponse getCustomersByPhone(Customer customer) {
        List<Customer> list= customerService.findCustomers(customer);
        return new FebsResponse().success().data(list.size()>0?list.get(0):new Customer());
    }

    @Log("新增Customer")
    @PostMapping("customer")
    @ResponseBody
    @RequiresPermissions("customer:add")
    public FebsResponse addCustomer(@Valid Customer customer) throws FebsException {
        try {
            this.customerService.createCustomer(customer);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "新增Customer失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除Customer")
    @GetMapping("customer/delete")
    @ResponseBody
    @RequiresPermissions("customer:delete")
    public FebsResponse deleteCustomer(Customer customer) throws FebsException {
        try {
            this.customerService.deleteCustomer(customer);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "删除Customer失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改Customer")
    @PostMapping("customer/update")
    @ResponseBody
    @RequiresPermissions("customer:update")
    public FebsResponse updateCustomer(Customer customer) throws FebsException {
        try {
            this.customerService.updateCustomer(customer);
            return new FebsResponse().success();
        } catch (Exception e) {
            String message = "修改Customer失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("customer/excel")
    @ResponseBody
    @RequiresPermissions("customer:export")
    public void export(QueryRequest queryRequest, Customer customer, HttpServletResponse response) throws FebsException {
        try {
            List<Customer> customers = this.customerService.findCustomers(queryRequest, customer).getRecords();
            ExcelKit.$Export(Customer.class, response).downXlsx(customers, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
