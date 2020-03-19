package com.hdsx.appservice.mongo.service.impl;

import com.alibaba.fastjson.JSON;
import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import com.hdsx.appservice.mongo.service.MongoService;
import com.hdsx.appservice.mongo.service.bean.Book;
import com.hdsx.appservice.mongo.service.bean.PageModel;
import com.hdsx.appservice.mongo.service.bean.SpringbootPageable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * 分页查询数据
     */
    @Override
    @ApiOperation("分页查询数据")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "pageNum", value = "", required = true, dataType = "intx", paramType = "query"),
            }
    )
    public XbinResult findBookByPageNum(int pageNum) {
        SpringbootPageable pageable = new SpringbootPageable();
        PageModel pm=new PageModel();
        Query query = new Query();
        // 排序
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Direction.DESC, "price"));
        Sort sort = new Sort(orders);
        // 开始页
        pm.setPagenumber(pageNum);
        // 每页条数
        pm.setPagesize(5);
        // 排序
        pm.setSort(sort);
        pageable.setPage(pm);
        // 查询出一共的条数
        Long count = mongoTemplate.count(query, Book.class);
        // 查询
        List<Book> list = mongoTemplate.find(query.with(pageable), Book.class);
        // 将集合与分页结果封装
        // Page<Book> pageList = new PageImpl<Book>(list, pageable, count);
        Map<String, Object> pageList = new HashMap<>();
        pageList.put("list",list);
        pageList.put("count",count);
        return XbinResult.ok(pageList);
    }

    /**
     * 无视 - mogo 联表查询
     * 联表查询 暂不做测试
     */
    @Override
    @ApiOperation("连接查询")
    public XbinResult findBookInfo() {
        LookupOperation lookupOperation = LookupOperation.newLookup().
                // 关联从表名 - 子表名称
                from("images").
                // 主表关联字段 - 主表的主键
                localField("_id").
                // 从表关联的字段 - 子表的外键
                foreignField("mapperid").
                // 查询结果名 - 我直接用的子表的名字
                as("images");

        // 带条件查询可以选择添加下面的条件
        Criteria ordercri1 = Criteria.where("price").is(10);
        // 只查询有结果的学生
        // Criteria criteria = Criteria.where("studenAndgrade").not().size(0);
        // 只查询名字中带有文的
        // Criteria qqq = Criteria.where("bookinfo").regex("文");
        // AggregationOperation match = Aggregation.match(criteria);
        AggregationOperation match1= Aggregation.match(ordercri1);

        Aggregation counts = Aggregation.newAggregation(match1,lookupOperation);
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);
        List<Map> results = mongoTemplate.aggregate(counts,"book", Map.class).getMappedResults();
        System.out.println(JSON.toJSONString(results));
        return XbinResult.ok(results);

    }
}
