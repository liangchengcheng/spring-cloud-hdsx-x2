package com.hdsx.appservice.order.service.hystrix;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.order.OrderBean;
import com.hdsx.appservice.bean.order.OrderQueryBean;
import com.hdsx.appservice.bean.user.ChangePassWordBean;
import com.hdsx.appservice.bean.user.UserBean;
import com.hdsx.appservice.bean.user.UserQueryBean;
import com.hdsx.appservice.order.service.OrderService;
import org.springframework.stereotype.Component;

import static com.hdsx.appservice.Contract.FAIL;

@Component
public class OrderServiceHystrix implements OrderService {

    @Override
    public XbinResult getOrderList(OrderQueryBean orderQueryBean) {
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @Override
    public XbinResult getOrderById(String id) {
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @Override
    public XbinResult addOrder(OrderBean orderBean) {
        return XbinResult.build(FAIL, ResultEnum.ORDER_SAVE_FAIL.getMsg());
    }

    @Override
    public XbinResult editOrder(OrderBean orderBean) {
        return XbinResult.build(FAIL, ResultEnum.ORDER_SAVE_FAIL.getMsg());
    }
}

