package com.hdsx.appservice.user.service.hystrix;

import com.hdsx.appservice.bean.ResultEnum;
import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.bean.user.ChangePassWordBean;
import com.hdsx.appservice.bean.user.UserBean;
import com.hdsx.appservice.bean.user.UserQueryBean;
import com.hdsx.appservice.user.service.UserService;
import org.springframework.stereotype.Component;

import static com.hdsx.appservice.Contract.FAIL;

@Component
public class UserServiceHystrix implements UserService {

    @Override
    public XbinResult userLogin(UserBean userBean) {
        return XbinResult.build(FAIL, ResultEnum.ERROR.getMsg());
    }

    @Override
    public XbinResult changePassWord(ChangePassWordBean changePassWordBean) {
        return XbinResult.build(FAIL, ResultEnum.UPDATE_USER_ERROR.getMsg());
    }

    @Override
    public XbinResult addUser(UserBean userBean) {
        return XbinResult.build(FAIL, ResultEnum.SAVE_USER_ERROR.getMsg());
    }

    @Override
    public XbinResult editUser(UserBean userBean) {
        return XbinResult.build(FAIL, ResultEnum.UPDATE_USER_ERROR.getMsg());
    }

    @Override
    public XbinResult deleteUserByUserId(String id) {
        return XbinResult.build(FAIL, ResultEnum.UPDATE_USER_ERROR.getMsg());
    }

    @Override
    public XbinResult getUserByName(String username) {
        return XbinResult.build(FAIL, ResultEnum.QUERY_USER_ERROR.getMsg());
    }

    @Override
    public XbinResult getUserById(String id) {
        return XbinResult.build(FAIL, ResultEnum.QUERY_USER_ERROR.getMsg());
    }

    @Override
    public XbinResult getUserList(UserQueryBean userQueryBean) {
        return XbinResult.build(FAIL, ResultEnum.QUERY_USER_ERROR.getMsg());
    }
}

