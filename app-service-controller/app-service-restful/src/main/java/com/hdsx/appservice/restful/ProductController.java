package com.hdsx.appservice.restful;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.product.ProductBean;
import com.hdsx.appservice.bean.user.UserBean;
import com.hdsx.appservice.bean.user.UserQueryBean;
import com.hdsx.appservice.product.service.ProductService;
import com.hdsx.appservice.user.service.UserService;
import com.hdsx.appservice.utils.MD5Util;
import io.swagger.annotations.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.hdsx.appservice.Contract.FAIL;

@Api(value = "API-商品体系之基础功能", description = "商品服务", tags = "API-PRODUCT")
@RestController
@RequestMapping(value = "user")
public class ProductController {

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation("插入或者更新商品信息")
    @RequestMapping(value = "/InsertOrUpdateProduct", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult InsertOrUpdateProduct(@RequestBody ProductBean productBean, HttpServletRequest httpServletRequest) {
        try {
            return productService.InsertOrUpdateProduct(productBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @ApiOperation("获取商品信息详情-ID")
    @RequestMapping(value = "/getProductById", method = RequestMethod.GET)
    @ResponseBody
    public XbinResult getProductById(@RequestParam(name = "id", defaultValue = "", required = true) String id) {
        try {
            return productService.getProductById(id);
        } catch (Exception e) {
            logger.error("获取商品信息详情:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.PRODUCT_QUERY_FAIL.getMsg());
    }

}
