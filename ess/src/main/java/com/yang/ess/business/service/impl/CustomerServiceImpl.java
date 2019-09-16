package com.yang.ess.business.service.impl;

import com.yang.ess.business.entity.Customer;
import com.yang.ess.business.mapper.CustomerMapper;
import com.yang.ess.business.service.ICustomerService;
import com.yang.ess.common.entity.QueryRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
/**
 * 客户 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:00
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public IPage<Customer> findCustomers(QueryRequest request, Customer customer) {
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Customer> page = new Page<Customer>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Customer> findCustomers(Customer customer) {
	    LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
        if(customer.getPhone()!=null){
            queryWrapper.eq(Customer::getPhone,customer.getPhone());
        }
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createCustomer(Customer customer) {
        this.save(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer) {
        this.saveOrUpdate(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Customer customer) {
        LambdaQueryWrapper<Customer> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
