package com.hdsx.appservice.restful;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.user.UserBean;
import com.hdsx.appservice.bean.user.UserQueryBean;
import com.hdsx.appservice.user.service.UserService;
import com.hdsx.appservice.utils.MD5Util;
import io.swagger.annotations.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.hdsx.appservice.Contract.*;

@Api(value = "API-用户体系之基础功能", description = "用户登录服务", tags = "API-USER")
@RestController
@RequestMapping(value = "user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("普通用户登录系统")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult userLogin(@RequestBody UserBean userBean, HttpServletRequest httpServletRequest) {
        try {
            return userService.userLogin(userBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @ApiOperation("插入用户USER信息")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult addUser(@RequestBody UserBean userBean) {
        try {
            if (userBean != null) {
                String pwd = MD5Util.string2MD5("hdsx@1234");
                userBean.setPassword(pwd);
                return userService.addUser(userBean);
            } else {
                return XbinResult.build(0 , ResultEnum.UPDATE_USER_ERROR.getMsg());
            }
        } catch (Exception e) {
            logger.error("插入用户USER信息失败:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.UPDATE_USER_ERROR.getMsg());
    }

    @ApiOperation("获取用户信息列表")
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult getUserList(@RequestBody UserQueryBean queryAppBean) {
        try {
            return userService.getUserList(queryAppBean);
        } catch (Exception e) {
            logger.error("获取用户信息异常:{}", e.getMessage(), e);
        }
        return XbinResult.build(0 , ResultEnum.QUERY_USER_ERROR.getMsg());
    }

}
