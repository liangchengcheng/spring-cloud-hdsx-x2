package com.hdsx.appservice.product.service.hystrix;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.product.ProductBean;
import com.hdsx.appservice.bean.product.ProductNumBean;
import com.hdsx.appservice.product.service.ProductService;
import org.springframework.stereotype.Component;
import static com.hdsx.appservice.Contract.FAIL;

@Component
public class ProductServiceHystrix implements ProductService {
    @Override
    public XbinResult InsertOrUpdateProduct(ProductBean productBean) {
        return XbinResult.build(FAIL, ResultEnum.PRODUCT_SAVE_FAIL.getMsg());
    }

    @Override
    public XbinResult UpdateProductNumBean(ProductNumBean productNumBean) {
        return XbinResult.build(FAIL, ResultEnum.PRODUCT_SAVE_FAIL.getMsg());
    }

    @Override
    public XbinResult getProductById(String id) {
        return XbinResult.build(FAIL, ResultEnum.PRODUCT_QUERY_FAIL.getMsg());
    }
}

