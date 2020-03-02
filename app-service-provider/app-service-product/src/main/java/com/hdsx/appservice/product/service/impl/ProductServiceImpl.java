package com.hdsx.appservice.product.service.impl;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.product.ProductBean;
import com.hdsx.appservice.bean.product.ProductNumBean;
import com.hdsx.appservice.dao.ProductMapper;
import com.hdsx.appservice.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.hdsx.appservice.Contract.FAIL;

/**
 * PRODUCT信息管理 - 登录、查询、编辑
 */
@Api(value = "API - PRODUCT信息管理", description = "PRODUCT信息管理")
@RequestMapping("/product")
@RestController
@RefreshScope
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @ApiOperation("插入商品信息")
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public XbinResult InsertOrUpdateProduct(@RequestBody ProductBean productBean) {
        try {
            if (productBean == null ) {
                return XbinResult.build(FAIL, ResultEnum.PRODUCT_SAVE_FAIL.getMsg());
            }
            // 获取基本信息
            // 获取基本信息的ID
            String id = productBean.getId();
            // 判断库里之前有没有,update或者insert
            ProductBean productById = productMapper.getProductById(id);
            int flag = 0;
            if (productById != null) {
                flag = productMapper.editProduct(productBean);
            } else {
                flag = productMapper.addProduct(productBean);
            }

            if (flag == 1) {
                return XbinResult.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return XbinResult.build(FAIL, ResultEnum.PRODUCT_SAVE_FAIL.getMsg());
    }

    @ApiOperation("更新库存的微服务")
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public XbinResult UpdateProductNumBean(@RequestBody ProductNumBean productNumBean) {
        try {
            if (productNumBean == null ) {
                return XbinResult.build(FAIL, ResultEnum.PRODUCT_SAVE_FAIL.getMsg());
            }

            // 获取基本信息的ID
            String id = productNumBean.getId();
            // 判断库里之前有没有,update或者insert
            ProductBean productById = productMapper.getProductById(id);
            if (productById != null) {
                // 原来数据库的 数量 - 这次订单的数量
                int num = productById.getNum() - productNumBean.getNum() ;
                productNumBean.setNum(num);
                int flag = 0;
                flag = productMapper.updateProductNumBean(productNumBean);
                if (flag == 1) {
                    return XbinResult.ok();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return XbinResult.build(FAIL, ResultEnum.PRODUCT_SAVE_FAIL.getMsg());
    }

    @Override
    @ApiOperation("获取商品信息详情 - id")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "", required = true, dataType = "String", paramType = "query"),
            }
    )
    public XbinResult getProductById(String id) {
        try {
            ProductBean product = productMapper.getProductById(id);
            return XbinResult.ok(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return XbinResult.build(FAIL, ResultEnum.QUERY_ROLE_ERROR.getMsg());
    }

}
