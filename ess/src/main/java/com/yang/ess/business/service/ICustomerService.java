package com.yang.ess.business.service;

import com.yang.ess.business.entity.Customer;

import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 客户 Service接口
 *
 * @author MrBird
 * @date 2019-09-04 22:52:00
 */
public interface ICustomerService extends IService<Customer> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param customer customer
     * @return IPage<Customer>
     */
    IPage<Customer> findCustomers(QueryRequest request, Customer customer);

    /**
     * 查询（所有）
     *
     * @param customer customer
     * @return List<Customer>
     */
    List<Customer> findCustomers(Customer customer);

    /**
     * 新增
     *
     * @param customer customer
     */
    void createCustomer(Customer customer);

    /**
     * 修改
     *
     * @param customer customer
     */
    void updateCustomer(Customer customer);

    /**
     * 删除
     *
     * @param customer customer
     */
    void deleteCustomer(Customer customer);
}
