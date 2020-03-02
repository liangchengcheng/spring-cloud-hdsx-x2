package com.hdsx.appservice.user.service;

import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.user.ChangePassWordBean;
import com.hdsx.appservice.bean.user.UserBean;
import com.hdsx.appservice.bean.user.UserQueryBean;
import com.hdsx.appservice.user.service.hystrix.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 梁铖城
 * 2019年11月22日14:40:31
 * User管理
 */
@FeignClient(value = "app-service-user", path = "/user", fallback = UserServiceHystrix.class)
public interface UserService {

    /**
     * 登录接口
     */
    @PostMapping("/userLogin")
    XbinResult userLogin(UserBean userBean);

    /**
     * 重置密码 - 修改密码
     */
    @PostMapping("/changePassWord")
    XbinResult changePassWord(@RequestBody ChangePassWordBean changePassWordBean);


    /**
     * 添加用户
     */
    @PostMapping("/addUser")
    XbinResult addUser(@RequestBody UserBean userBean);

    /**
     * 添加用户
     */
    @PostMapping("/editUser")
    XbinResult editUser(@RequestBody UserBean userBean);

    /**
     * 根据ID删除用户 - 一般用post 比较安全
     */
    @GetMapping("/deleteUserByUserId")
    XbinResult deleteUserByUserId(@RequestParam("id") String id);

    /**
     * 获取用户 username
     */
    @GetMapping("/getUserByName")
    XbinResult getUserByName(@RequestParam("username") String username);

    /**
     * 获取用户 ID
     */
    @GetMapping("/getUserById")
    XbinResult getUserById(@RequestParam("id") String id);

    /**
     * 获取用户列表
     */
    @PostMapping("/getUserList")
    XbinResult getUserList(@RequestBody UserQueryBean userQueryBean);


}