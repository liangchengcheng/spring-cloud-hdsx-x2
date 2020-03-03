package com.hdsx.appservice.order.service.impl;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.order.OrderBean;
import com.hdsx.appservice.bean.order.OrderQueryBean;
import com.hdsx.appservice.bean.product.ProductNumBean;
import com.hdsx.appservice.bean.user.ChangePassWordBean;
import com.hdsx.appservice.bean.user.UserBean;
import com.hdsx.appservice.bean.user.UserQueryBean;
import com.hdsx.appservice.dao.OrderMapper;
import com.hdsx.appservice.dao.UserMapper;
import com.hdsx.appservice.order.service.OrderService;
import com.hdsx.appservice.product.service.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import static com.hdsx.appservice.Contract.FAIL;

/**
 * USER信息管理 - 登录、查询、编辑
 */
@Api(value = "API - 订单信息管理", description = "订单信息管理")
@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductService productService;

    @ApiOperation("插入订单的信息（插入和更新状态判断）")
    // 微服务要用分布式事物 - 这个单机事物不行
    // @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    @Override
    public XbinResult editOrder(@RequestBody OrderBean orderBean) {
        try {
            if (orderBean == null ) {
                return XbinResult.build(FAIL, ResultEnum.PARAMS_ERROR.getMsg());
            }
            int flag = orderMapper.editOrder(orderBean);
            if (flag == 1) {
                return XbinResult.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @ApiOperation("更新USER的信息")
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    @Override
    public XbinResult addOrder(@RequestBody OrderBean orderBean) {
        try {
            if (orderBean == null ) {
                return XbinResult.build(FAIL, ResultEnum.PARAMS_ERROR.getMsg());
            }

            // 获取基本信息的ID
            String id = orderBean.getId();
            // 判断库里之前有没有,update或者insert
            OrderBean orderById = orderMapper.getOrderById(id);
            int flag = 0;
            if (orderById != null) {
                // 修改订单
                flag = orderMapper.editOrder(orderBean);
            } else {
                // 逻辑简单，只做演示，这里只有新添加的订单才减少库存
                ProductNumBean productNumBean = new ProductNumBean();
                productNumBean.setNum(orderBean.getOrdernum());
                productNumBean.setId(orderBean.getProductid());
                productService.UpdateProductNumBean(productNumBean);
                // 生存订单
                flag = orderMapper.addOrder(orderBean);
            }

            if (flag == 1) {
                return XbinResult.ok(flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ORDER_SAVE_FAIL.getMsg());
    }

    @Override
    @ApiOperation("获取订单信息的列表")
    public XbinResult getOrderList(@RequestBody OrderQueryBean orderQueryBean) {
        try {
            if (orderQueryBean == null) {
                return XbinResult.build(FAIL, ResultEnum.PARAMS_ERROR.getMsg());
            }

            List<OrderBean> orderList = orderMapper.getOrderList(orderQueryBean);
            return XbinResult.ok(orderList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @Override
    @ApiOperation("获取订单的详情- 通过ID获取 - id")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "", required = true, dataType = "String", paramType = "query"),
            }
    )
    public XbinResult getOrderById(String id) {
        try {
            OrderBean orderById = orderMapper.getOrderById(id);
            return XbinResult.ok(orderById);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

}
