package com.hdsx.appservice.mongo.service;

import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.order.OrderBean;
import com.hdsx.appservice.bean.order.OrderQueryBean;

import com.hdsx.appservice.mongo.service.bean.Book;
import com.hdsx.appservice.mongo.service.hystrix.MongoServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 梁铖城
 * 2019年11月22日14:40:31
 * 订单管理
 */
@FeignClient(value = "app-service-mongo", path = "/book", fallback = MongoServiceHystrix.class)
public interface MongoService {

    /**
     * 添加书籍
     */
    @PostMapping("/saveBook")
    XbinResult saveBook(Book book);

    /**
     * 获取全部数据
     */
    @GetMapping("/findAll")
    XbinResult findAll(@RequestParam("id") String id);

    /**
     * 根据id查询
     */
    @GetMapping("/getBookById")
    XbinResult getBookById(@RequestParam("id") String id);

    /**
     * 添加书籍信息
     */
    @PostMapping("/updateBook")
    XbinResult updateBook(@RequestBody Book book);

    /**
     * 删除书籍
     */
    @PostMapping("/deleteBook")
    XbinResult deleteBook(@RequestBody Book book);

    @GetMapping("/deleteBookById")
    XbinResult deleteBookById(@RequestParam("id") String id);

    /**
     * like查询
     */
    @GetMapping("/findByLikes")
    XbinResult findByLikes(@RequestParam("search") String search);

    /**
     * 编辑订单信息
     */
    @GetMapping("/findBookByPageNum")
    XbinResult findBookByPageNum(@RequestParam("pageNum") int pageNum);

    /**
     * 连接查询
     */
    @GetMapping("/findBookInfo")
    XbinResult findBookInfo();

}