package com.hdsx.appservice.mongo.service.impl;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.order.OrderBean;
import com.hdsx.appservice.bean.order.OrderQueryBean;
import com.hdsx.appservice.bean.product.ProductNumBean;
import com.hdsx.appservice.dao.OrderMapper;
import com.hdsx.appservice.mongo.service.MongoService;
import com.hdsx.appservice.mongo.service.bean.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.List;
import java.util.regex.Pattern;

import static com.hdsx.appservice.Contract.FAIL;

/**
 * Mongo
 */
@Api(value = "API - Mongo", description = "Mongo")
@RestController
@RequestMapping("/mongo")
@RefreshScope
public class MongoServiceImpl implements MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @ApiOperation("插入Book")
    @Override
    public XbinResult saveBook(@RequestBody Book book) {
        try {
            if (book == null ) {
                return XbinResult.build(FAIL, ResultEnum.PARAMS_ERROR.getMsg());
            }

            mongoTemplate.save(book);
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
            List<Book> all = mongoTemplate.findAll(Book.class);
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
            Query query = new Query(Criteria.where("_id").is(id));
            Book one = mongoTemplate.findOne(query, Book.class);
            return XbinResult.ok(one);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @ApiOperation("更新Book-mongo")
    @Override
    public XbinResult updateBook(@RequestBody Book book) {
        try {
            if (book == null ) {
                return XbinResult.build(FAIL, ResultEnum.PARAMS_ERROR.getMsg());
            }


            return XbinResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @ApiOperation("删除Book-通过对象")
    @Override
    public XbinResult deleteBook(@RequestBody Book book) {
        try {
            if (book == null ) {
                return XbinResult.build(FAIL, ResultEnum.PARAMS_ERROR.getMsg());
            }

            mongoTemplate.remove(book);
            return XbinResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @Override
    @ApiOperation("通过ID删除")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "", required = true, dataType = "String", paramType = "query"),
            }
    )
    public XbinResult deleteBookById(String id) {
        try {
            if (id == null ) {
                return XbinResult.build(FAIL, ResultEnum.PARAMS_ERROR.getMsg());
            }
            Query query = new Query(Criteria.where("_id").is(id));
            Book one = mongoTemplate.findOne(query, Book.class);
            // delete
            deleteBook(one);
            return XbinResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @Override
    @ApiOperation("模糊查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "search", value = "", required = true, dataType = "String", paramType = "query"),
            }
    )
    public XbinResult findByLikes(String search) {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            // criteria.where("name").regex(search);
            Pattern pattern = Pattern.compile("^.*" + search + ".*$" , Pattern.CASE_INSENSITIVE);
            criteria.where("name").regex(pattern);
            List<Book> lists = mongoTemplate.findAllAndRemove(query, Book.class);
            return XbinResult.ok(lists);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }
}
