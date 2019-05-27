package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.OrderBean;
import com.znsd.instapundit.bxwserver.dao.OrderDao;
import com.znsd.instapundit.param.OrderParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public PagingResult pagingList(OrderParam param) {
        List<OrderBean> orderList = orderDao.pagingList(param);
        int count = orderDao.getPagingCount(param);
        return PagingResult.success(count, orderList);
    }

    @Override
    public List<OrderBean> getMyOrder(Integer status,Integer id) {
        return orderDao.getMyOrder(status,id);
    }

    @Override
    public int deleteOrder(Integer id) {
        return orderDao.deleteOrder(id);
    }

    @Override
    public OrderBean getOrderByOid(Long oid) {
        return orderDao.getOrderByOid(oid);
    }

    @Override
    public int getCenterList() {
       List<OrderBean> order= orderDao.getCenterList();
        int count =0;
        if (order.size()>0){
             count =orderDao.centerOrder(order);
        }

        return count;
    }
}
