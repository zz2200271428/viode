package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.OrderBean;
import com.znsd.instapundit.param.OrderParam;
import com.znsd.instapundit.result.PagingResult;

import java.util.List;

public interface OrderService {

    PagingResult pagingList(OrderParam param);

    List<OrderBean> getMyOrder(Integer status,Integer id);

    int deleteOrder(Integer id);

    OrderBean getOrderByOid(Long oid);

    int getCenterList();
}
