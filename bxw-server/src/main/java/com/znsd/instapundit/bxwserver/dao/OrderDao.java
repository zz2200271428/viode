package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.OrderBean;
import com.znsd.instapundit.param.OrderParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {

    List<OrderBean> pagingList(OrderParam param);

    int getPagingCount(OrderParam param);

    List<OrderBean> getMyOrder(@Param("status") Integer status,@Param("id")Integer id);

    int deleteOrder(@Param("id") Integer id);

    OrderBean getOrderByOid(@Param("oid") Long oid);

    List<OrderBean> getCenterList();

    int centerOrder(@Param("list") List<OrderBean> order);
}
