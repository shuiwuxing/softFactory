package com.yang.ess.business.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yang.ess.business.entity.*;
import com.yang.ess.business.mapper.OutWarehouseMapper;
import com.yang.ess.business.service.ICustomerService;
import com.yang.ess.business.service.IInWarehouseDetailService;
import com.yang.ess.business.service.IOutWarehouseDetailService;
import com.yang.ess.business.service.IOutWarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import com.yang.ess.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 出库单 Service实现
 *
 * @author MrBird
 * @date 2019-09-04 22:52:30
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OutWarehouseServiceImpl extends ServiceImpl<OutWarehouseMapper, OutWarehouse> implements IOutWarehouseService {

    @Autowired
    private OutWarehouseMapper outWarehouseMapper;
    @Autowired
    private IOutWarehouseDetailService IOutWarehouseDetailService;
    @Autowired
    private IInWarehouseDetailService inWarehouseDetailService;
    @Autowired
    private ICustomerService customerService;

    @Override
    public IPage<OutWarehouse> findOutWarehouses(QueryRequest request, OutWarehouse outWarehouse) {
        LambdaQueryWrapper<OutWarehouse> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        if(outWarehouse.getOrderNo()!=null){
            queryWrapper.like(OutWarehouse::getOrderNo,outWarehouse.getOrderNo());
        }
        queryWrapper.orderByDesc(OutWarehouse::getId);
        Page<OutWarehouse> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<OutWarehouse> findOutWarehouses(OutWarehouse outWarehouse) {
	    LambdaQueryWrapper<OutWarehouse> queryWrapper = new LambdaQueryWrapper<>();
	    queryWrapper.like(OutWarehouse::getOrderNo,outWarehouse.getOrderNo() );
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createOutWarehouse(OutWarehouse outWarehouse,String detail,String Name,String Phone) {
        outWarehouse.setCreateTime(new Date());
        //处理客户信息
        if(outWarehouse.getCid()==null){
                Customer customer=new Customer();
                customer.setName(Name);
                customer.setPhone(Phone);
                customer.setCreateTime(new Date());
                customerService.save(customer);
        }
        outWarehouse.setStatus(1);
        this.save(outWarehouse);
        JSONObject jsonObject=new JSONObject();
        List<Map> details=JSON.parseArray(detail, Map.class);
        InWarehouseDetail condition=new InWarehouseDetail();
        for (int i=0;i<details.size();i++){
            OutWarehouseDetail outWarehouseDetail =new OutWarehouseDetail();
            outWarehouseDetail.setWid(outWarehouse.getId());
            String startChar=details.get(i).get("imei").toString().substring(0,1);
            if("a".equals(startChar)){
                outWarehouseDetail.setType(2);
            }else if("b".equals(startChar)){
                outWarehouseDetail.setType(3);
            }else{
                outWarehouseDetail.setType(1);
            }
            outWarehouseDetail.setGid(Integer.parseInt(details.get(i).get("goodsId").toString()));
            outWarehouseDetail.setImei(details.get(i).get("imei").toString());
            outWarehouseDetail.setMoney(Integer.parseInt(details.get(i).get("price").toString()));
            outWarehouseDetail.setNum(Integer.parseInt(details.get(i).get("num").toString()));
            outWarehouseDetail.setStatus(1);
            condition.setImei(details.get(i).get("imei").toString());
            InWarehouseDetail inWarehouseDetail=inWarehouseDetailService.findInWarehouseDetails(condition).get(0);
            inWarehouseDetail.setStatus(2);
            inWarehouseDetailService.updateInWarehouseDetail(inWarehouseDetail);
            IOutWarehouseDetailService.save(outWarehouseDetail);
        }
    }

    @Override
    @Transactional
    public void updateOutWarehouse(OutWarehouse outWarehouse) {
        this.saveOrUpdate(outWarehouse);
    }

    @Override
    @Transactional
    public void deleteOutWarehouse(OutWarehouse outWarehouse) {
        LambdaQueryWrapper<OutWarehouse> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
