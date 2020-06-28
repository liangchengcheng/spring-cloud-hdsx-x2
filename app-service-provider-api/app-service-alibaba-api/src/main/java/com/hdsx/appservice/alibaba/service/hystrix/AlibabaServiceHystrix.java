package com.hdsx.appservice.alibaba.service.hystrix;

import com.hdsx.appservice.alibaba.service.AlibabaService;
import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.alibaba.service.bean.Book;
import org.springframework.stereotype.Component;

import static com.hdsx.appservice.Contract.FAIL;

@Component
public class AlibabaServiceHystrix implements AlibabaService {

    @Override
    public XbinResult saveBook(Book book) {
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @Override
    public XbinResult findAll(String id) {
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @Override
    public XbinResult getBookById(String id) {
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

}

