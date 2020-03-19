package com.hdsx.appservice.mongo.service.hystrix;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.mongo.service.MongoService;
import com.hdsx.appservice.mongo.service.bean.Book;
import org.springframework.stereotype.Component;

import static com.hdsx.appservice.Contract.FAIL;

@Component
public class MongoServiceHystrix implements MongoService {

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

    @Override
    public XbinResult updateBook(Book book) {
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @Override
    public XbinResult deleteBook(Book book) {
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @Override
    public XbinResult deleteBookById(String id) {
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @Override
    public XbinResult findByLikes(String search) {
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @Override
    public XbinResult findBookByPageNum(int pageNum) {
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @Override
    public XbinResult findBookInfo() {
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }
}

