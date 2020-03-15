package com.hdsx.appservice.restful;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.order.OrderBean;
import com.hdsx.appservice.bean.order.OrderQueryBean;
import com.hdsx.appservice.mongo.service.MongoService;
import com.hdsx.appservice.mongo.service.bean.Book;
import com.hdsx.appservice.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "API-Mongo之基础功能", description = "Mongo之基础功能", tags = "API-MONGO")
@RestController
@RequestMapping(value = "user")
public class MongoController {

    public static final Logger logger = LoggerFactory.getLogger(MongoController.class);

    @Autowired
    private MongoService mongoService;

    @ApiOperation("插入图书信息")
    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult saveBook(@RequestBody Book book) {
        try {
            if (book != null) {
                return mongoService.saveBook(book);
            }
        } catch (Exception e) {
            logger.error("插入图书信息:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.ERROR.getMsg());
    }

    @ApiOperation("获取全部图书信息")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public XbinResult findAll(@RequestParam(name = "id", defaultValue = "", required = true) String id) {
        try {
            return mongoService.findAll(id);
        } catch (Exception e) {
            logger.error("获取全部图书信息:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @ApiOperation("获取图书信息-通过ID")
    @RequestMapping(value = "/getBookById", method = RequestMethod.GET)
    @ResponseBody
    public XbinResult getBookById(@RequestParam(name = "id", defaultValue = "", required = true) String id) {
        try {
            return mongoService.getBookById(id);
        } catch (Exception e) {
            logger.error("获取全部图书信息:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

    @ApiOperation("更新图书信息")
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult updateBook(@RequestBody Book book) {
        try {
            if (book != null) {
                return mongoService.updateBook(book);
            }
        } catch (Exception e) {
            logger.error("更新图书信息:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.ERROR.getMsg());
    }

    @ApiOperation("删除图书信息")
    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult deleteBook(@RequestBody Book book) {
        try {
            if (book != null) {
                return mongoService.deleteBook(book);
            }
        } catch (Exception e) {
            logger.error("删除图书信息:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.ERROR.getMsg());
    }

    @ApiOperation("获取图书信息-通过模糊查询")
    @RequestMapping(value = "/findByLikes", method = RequestMethod.GET)
    @ResponseBody
    public XbinResult findByLikes(@RequestParam(name = "search", defaultValue = "", required = true) String search) {
        try {
            return mongoService.findByLikes(search);
        } catch (Exception e) {
            logger.error("获取全部图书信息:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.ORDER_QUERY_FAIL.getMsg());
    }

}
