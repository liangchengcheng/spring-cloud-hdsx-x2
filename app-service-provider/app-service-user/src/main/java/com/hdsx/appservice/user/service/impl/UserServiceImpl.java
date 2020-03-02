package com.hdsx.appservice.user.service.impl;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.user.ChangePassWordBean;
import com.hdsx.appservice.bean.user.UserBean;
import com.hdsx.appservice.bean.user.UserQueryBean;
import com.hdsx.appservice.dao.UserMapper;
import com.hdsx.appservice.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import static com.hdsx.appservice.Contract.FAIL;

/**
 * USER信息管理 - 登录、查询、编辑
 */
@Api(value = "API - USER信息管理", description = "USER信息管理")
@RequestMapping("/user")
@RestController
@RefreshScope
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @ApiOperation("用户登录的服务")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(dataType = "UserBean", name = "userBean", value = "用户登录服务", required = true)
            }
    )
    public XbinResult userLogin(@RequestBody UserBean userBean) {

        try {
            if (userBean == null || StringUtils.isEmpty(userBean.getUsername()) || StringUtils.isEmpty(userBean.getPassword())) {
                return XbinResult.build(FAIL, ResultEnum.QUERY_USER_ERROR.getMsg());
            }

            /**
             * 只做简单校验、密码也没md5、正式项目不可如此，
             * 这里返回的是用户基本信息，正式项目返回基本信息 + token
             */
            String username = userBean.getUsername();
            String pwd0 = userBean.getPassword();
            UserBean userByName = userMapper.getUserByName(username);

            // 校验用户是否存在
            if (userByName == null) {
                return XbinResult.build(FAIL, ResultEnum.NUKONW_USER_ERROR.getMsg());
            }

            // 校验用户是否被禁用
            if (userByName.getState() == 1) {
                return XbinResult.build(FAIL, ResultEnum.DISABLED_USER_ERROR.getMsg());
            }

            // 校验密码是否正确
            String pwd1 = userByName.getPassword();
            if (!pwd0.equals(pwd1)) {
                return XbinResult.build(FAIL, ResultEnum.PASS_USER_ERROR.getMsg());
            }

            // 返回用户基本信息
            return XbinResult.ok(userByName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @ApiOperation("更新USER的信息的- 重置密码")
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public XbinResult changePassWord(@RequestBody ChangePassWordBean changePassWordBean) {
        try {
            if (changePassWordBean == null || StringUtils.isEmpty(changePassWordBean.getUsername())
                    || StringUtils.isEmpty(changePassWordBean.getPassword()) || StringUtils.isEmpty(changePassWordBean.getKey())) {
                return XbinResult.build(FAIL, ResultEnum.QUERY_USER_ERROR.getMsg());
            }

            /**
             * 只做简单校验、密码也没md5、正式项目不可如此，
             * 这里返回的是用户基本信息，正式项目返回基本信息 + token
             */
            String username = changePassWordBean.getUsername();
            String pwd0 = changePassWordBean.getPassword();
            UserBean userByName = userMapper.getUserByName(username);

            // 校验用户是否存在
            if (userByName == null) {
                return XbinResult.build(FAIL, ResultEnum.NUKONW_USER_ERROR.getMsg());
            }

            // 校验用户是否被禁用
            if (userByName.getState() == 1) {
                return XbinResult.build(FAIL, ResultEnum.DISABLED_USER_ERROR.getMsg());
            }

            // 校验密码是否正确
            String pwd1 = userByName.getPassword();
            if (!pwd0.equals(pwd1)) {
                return XbinResult.build(FAIL, ResultEnum.PASS_USER_ERROR.getMsg());
            }

            // 校验KEY
            String key = changePassWordBean.getKey();
            int flag = userMapper.changePassWord(changePassWordBean);
            if (flag == 1) {
                // 返回用户基本信息
                return XbinResult.ok(userByName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @ApiOperation("插入USER的信息（插入和更新状态判断）")
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public XbinResult addUser(@RequestBody UserBean userBean) {
        try {
            if (userBean == null) {
                return XbinResult.build(FAIL, ResultEnum.UPDATE_USER_ERROR.getMsg());
            }

            int flag = userMapper.addUser(userBean);
            if (flag == 1) {
                return XbinResult.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.UPDATE_USER_ERROR.getMsg());
    }

    @ApiOperation("更新USER的信息")
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public XbinResult editUser(@RequestBody UserBean userBean) {
       try {
           int i = userMapper.editUser(userBean);
           if (i == 1) {
               return XbinResult.ok();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return XbinResult.build(FAIL, ResultEnum.UPDATE_USER_ERROR.getMsg());
    }

    @Override
    @ApiOperation("获取User信息的详情- username")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "username", value = "", required = true, dataType = "String", paramType = "query"),
            }
    )
    public XbinResult getUserByName(String username) {
        try {
            UserBean userByName = userMapper.getUserByName(username);
            return XbinResult.ok(userByName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.QUERY_USER_ERROR.getMsg());
    }

    @Override
    @ApiOperation("获取User信息的详情- 权限简单版 - userid")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "", required = true, dataType = "String", paramType = "query"),
            }
    )
    public XbinResult getUserById(String id) {
        try {
            UserBean userById = userMapper.getUserById(id);
            return XbinResult.ok(userById);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XbinResult.build(FAIL, ResultEnum.QUERY_ROLE_ERROR.getMsg());
    }

    /**
     * 暂不实现 删除的微服务
     */
    @Override
    public XbinResult deleteUserByUserId(String id) {
        return null;
    }

    @Override
    @ApiOperation("获取User信息的列表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(dataType = "UserQueryBean", name = "userQueryBean", value = "用户登录服务", required = true)
            }
    )
    public XbinResult getUserList(@RequestBody UserQueryBean userQueryBean) {
        try {
            if (userQueryBean == null) {
                return XbinResult.build(FAIL, ResultEnum.QUERY_USER_ERROR.getMsg());
            }

            Map<Object, Object> map = new HashMap<>();
            List<UserBean> userList = userMapper.getUserList(userQueryBean);
            map.put("data",userList);
            map.put("total", 0);

            if (userList != null && userList.size() > 0) {
                int total = userMapper.getUserListCount(userQueryBean);
                map.put("total", total);
            }

            return XbinResult.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return XbinResult.build(FAIL, ResultEnum.QUERY_USER_ERROR.getMsg());
    }

}
