package com.hdsx.appservice.bean;

/**
 *
 * 返回的错误信息
 */
public enum ResultEnum {
    ERROR(0, "系统异常，请求错误"),
    SUCCESS(1, "请求成功"),
    TOKEN_LOSE(2,"为了保证安全，需要重新登录"),
    NAME_REPEATABILITY(0,"单位名称重复"),

    NUKONW_USER_ERROR(0, "用户未注册"),
    DISABLED_USER_ERROR(2, "用户已停用"),
    QUERY_USER_ERROR(0, "查询用户失败"),
    QUERY_ROLE_ERROR(0, "查询用户权限失败"),
    UPDATE_ROLE_ERROR(0, "更新用户权限失败"),
    PASS_USER_ERROR(0, "密码不正确"),
    DELETE_USER_ERROR(0,"删除用户失败"),
    SAVE_USER_ERROR(0,"新增用户失败"),
    UPDATE_USER_ERROR(0,"新增或者更新用户失败"),
    ENABLE_USER_ERROR(0,"启用用户失败"),
    DISABLE_USER_ERROR(0,"禁用用户失败"),
    MODFIYPWD_USER_ERROR(0,"旧密码输入错误"),
    SET_USER_ERROR(0,"新密码和确认密码输入不一致"),
    VERIFY_USER_SUCCESS(0,"与原密码相符"),
    VERIFY_USER_ERROR(0,"与原密码不符"),
    SET_USER_ERROR2(0,"修改密码失败"),
    MODFIYPWD_USER_ERROR2(0,"修改密码失败"),
    TEAM_REPETITION(0,"分组名称重复"),
    TEAM_MEMBER_NUKONW(0,"分组用户不存在"),
    SAVE_USER_USERNAME(0,"用户名已存在"),
    QUERY_APP_INFO_TIME_OUT(0,"获取APP信息异常"),
    QUERY_VUE_INFO_TIME_OUT(0,"获取前端信息异常"),
    UPDATE_APP_INFO_TIME_OUT(0,"插入或更新APP信息异常"),
    UPDATE_VUE_INFO_TIME_OUT(0,"插入或更新前端信息异常"),
    ISNONEWCODE(0,"请将软件更新至最新版本，否则无法使用"),
    GET_LOG_ERROR(0,"获取操作日志失败"),
    GET_VUE_ERROR(0,"获取前端信息失败"),
    UPDATE_VUE_ERROR(0,"获取前端信息失败"),
    PRODUCT_QUERY_FAIL(0,"获取产品信息失败"),
    PRODUCT_SAVE_FAIL(0,"保存或者编辑产品信息失败"),

    ORDER_QUERY_FAIL(0,"获取订单信息失败"),
    ORDER_SAVE_FAIL(0,"保存或者编辑订单信息失败"),

    NOT_BIND_WEIXIN(9, "未绑定"),
    PARAMS_ERROR(0, "参数错误"),
    BIND_ERROR(0, "绑定失败"),
    ERROR_1(0, "请求失败"),
    UER_ERROR(0,"用户失败次数过多，已被禁用，请联系管理员"),
    ERROR_CAM_NOT_UPLOAD(0, "暂时不能上传");




    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
