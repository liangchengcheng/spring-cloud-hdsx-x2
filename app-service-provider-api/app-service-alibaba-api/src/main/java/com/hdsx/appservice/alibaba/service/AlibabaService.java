package com.hdsx.appservice.alibaba.service;

import com.hdsx.appservice.bean.XbinResult;

import com.hdsx.appservice.alibaba.service.bean.Book;
import com.hdsx.appservice.alibaba.service.hystrix.AlibabaServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 梁铖城
 * 2019年11月22日14:40:31
 * nacas seata sentinel
 */
@FeignClient(value = "app-service-alibaba", path = "/alibaba", fallback = AlibabaServiceHystrix.class)
public interface AlibabaService {

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

}