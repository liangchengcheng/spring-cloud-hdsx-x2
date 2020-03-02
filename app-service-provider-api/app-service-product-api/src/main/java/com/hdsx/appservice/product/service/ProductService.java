package com.hdsx.appservice.product.service;

import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.product.ProductBean;
import com.hdsx.appservice.bean.product.ProductNumBean;
import com.hdsx.appservice.product.service.hystrix.ProductServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 梁铖城
 * 2019年11月22日14:40:31
 * 产品管理
 */
@FeignClient(value = "app-service-product", path = "/product", fallback = ProductServiceHystrix.class)
public interface ProductService {

    /**
     * 添加或者编辑产品信息系
     */
    @PostMapping("/InsertOrUpdateProduct")
    XbinResult InsertOrUpdateProduct(@RequestBody ProductBean productBean);

    /**
     * 添加或者编辑产品信息系
     */
    @PostMapping("/UpdateProductNumBean")
    XbinResult UpdateProductNumBean(@RequestBody ProductNumBean productNumBean);

    /**
     * 获取商品的详情
     */
    @GetMapping("/getProductById")
    XbinResult getProductById(@RequestParam("id") String id);

}