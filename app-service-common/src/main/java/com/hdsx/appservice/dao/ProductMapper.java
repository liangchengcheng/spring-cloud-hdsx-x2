package com.hdsx.appservice.dao;

import com.hdsx.appservice.bean.product.ProductBean;
import com.hdsx.appservice.bean.product.ProductNumBean;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    /**
     * 添加商品信息
     */
    int addProduct(ProductBean productBean);

    /**
     * 修改商品信息
     */
    int updateProductNumBean(ProductNumBean productNumBean);

    /**
     * 修改商品信息
     */
    int editProduct(ProductBean productBean);

    /**
     * 通过ID获取商品信息
     */
    ProductBean getProductById(String id);
}
