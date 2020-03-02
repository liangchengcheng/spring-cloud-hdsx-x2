package com.hdsx.appservice.order.service;

import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.order.OrderBean;
import com.hdsx.appservice.bean.order.OrderQueryBean;
import com.hdsx.appservice.bean.user.ChangePassWordBean;
import com.hdsx.appservice.bean.user.UserBean;
import com.hdsx.appservice.bean.user.UserQueryBean;
import com.hdsx.appservice.order.service.hystrix.OrderServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 梁铖城
 * 2019年11月22日14:40:31
 * 订单管理
 */
@FeignClient(value = "app-service-order", path = "/order", fallback = OrderServiceHystrix.class)
public interface OrderService {

    /**
     * 获取订单列表 - POST
     */
    @PostMapping("/getOrderList")
    XbinResult getOrderList(OrderQueryBean orderQueryBean);

    /**
     * 根据ID获取订单详情
     */
    @GetMapping("/getOrderById")
    XbinResult getOrderById(@RequestParam("id") String id);

    /**
     * 添加订单信息
     */
    @PostMapping("/addOrder")
    XbinResult addOrder(@RequestBody OrderBean orderBean);

    /**
     * 编辑订单信息
     */
    @PostMapping("/editOrder")
    XbinResult editOrder(@RequestBody OrderBean orderBean);

}