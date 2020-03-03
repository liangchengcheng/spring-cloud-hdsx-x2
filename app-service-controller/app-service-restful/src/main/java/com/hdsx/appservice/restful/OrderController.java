package com.hdsx.appservice.restful;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.order.OrderBean;
import com.hdsx.appservice.bean.order.OrderQueryBean;
import com.hdsx.appservice.bean.user.UserBean;
import com.hdsx.appservice.bean.user.UserQueryBean;
import com.hdsx.appservice.order.service.OrderService;
import com.hdsx.appservice.user.service.UserService;
import com.hdsx.appservice.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.hdsx.appservice.Contract.FAIL;

@Api(value = "API-订单体系之基础功能", description = "订单相关服务", tags = "API-ORDER")
@RestController
@RequestMapping(value = "user")
public class OrderController {

    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @ApiOperation("插入订单信息")
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult addOrder(@RequestBody OrderBean orderBean) {
        try {
            if (orderBean != null) {
                return orderService.addOrder(orderBean);
            }
        } catch (Exception e) {
            logger.error("插入订单信息:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.UPDATE_USER_ERROR.getMsg());
    }

    @ApiOperation("获取订单信息详情-ID")
    @RequestMapping(value = "/getOrderById", method = RequestMethod.GET)
    @ResponseBody
    public XbinResult getOrderById(@RequestParam(name = "id", defaultValue = "", required = true) String id) {
        try {
            return orderService.getOrderById(id);
        } catch (Exception e) {
            logger.error("获取订单信息详情:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @ApiOperation("获取订单信息列表")
    @RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult getOrderList(@RequestBody OrderQueryBean orderQueryBean) {
        try {
            return orderService.getOrderList(orderQueryBean);
        } catch (Exception e) {
            logger.error("获取订单信息列表:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

}
