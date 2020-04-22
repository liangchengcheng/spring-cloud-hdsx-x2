package com.hdsx.appservice.dao;

import com.hdsx.appservice.bean.order.OrderBean;
import com.hdsx.appservice.bean.order.OrderQueryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 梁铖城
 * 2020年03月03日10:24:23
 * 订单 mapper - 放在对应的微服务的包下最好
 */
@Repository
public interface OrderMapper {
    /**
     * 查询订单的列表
     */
    List<OrderBean> getOrderList(OrderQueryBean orderQueryBean);

    int getOrderListNum(OrderQueryBean orderQueryBean);

    OrderBean getOrderById(String id);

    /**
     * 添加用户
     */
    int addOrder(OrderBean orderBean);

    /**
     * 修改用户
     */
    int editOrder(OrderBean orderBean);

    int editOrderState(List<OrderBean> orderBean);

    /**
     * 取消订单
     */
    int cancelOrderById(String id);

    /**
     * 查询超过发货了且，且updatetime距离现在10天
     */
    List<OrderBean> getOrderListFor7Days(OrderQueryBean orderQueryBean);
}