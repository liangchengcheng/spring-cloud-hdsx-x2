package com.hdsx.appservice.alibaba.service.impl;

import com.hdsx.appservice.alibaba.service.AlibabaService;
import com.hdsx.appservice.alibaba.service.bean.Book;
import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import static com.hdsx.appservice.Contract.FAIL;

/**
 * Mongo
 */
@Api(value = "API - Alibaba", description = "Alibaba")
@RestController
@RequestMapping("/alibaba")
@RefreshScope
public class AlibabaServiceImpl implements AlibabaService {

    // 用mybatis

    @ApiOperation("插入Book")
    @Override
    public XbinResult saveBook(@RequestBody Book book) {
        try {
            if (book == null ) {
                return XbinResult.build(FAIL, ResultEnum.PARAMS_ERROR.getMsg());
            }
            // sql save(book);
            return XbinResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @Override
    @ApiOperation("获取全部Book列表")
    public XbinResult findAll(String id) {
        try {
            // sql query all book
            List<Book> all = new ArrayList<>();
            return XbinResult.ok(all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @Override
    @ApiOperation("获取Book的详情- 通过ID获取 - id")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "", required = true, dataType = "String", paramType = "query"),
            }
    )
    public XbinResult getBookById(String id) {
        try {
            // sql query book by id
            Book one = new Book();
            return XbinResult.ok(one);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }
}
