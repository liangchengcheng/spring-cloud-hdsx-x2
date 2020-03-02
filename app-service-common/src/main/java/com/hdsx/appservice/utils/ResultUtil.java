package com.hdsx.appservice.utils;

import com.hdsx.appservice.bean.ApiResult;
import com.hdsx.appservice.bean.ResultEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 梁铖城 on 2017/7/19.
 */
public class ResultUtil {

    public static ApiResult success(ResultEnum resultEnum, Map<String, Object> map) {
        List<Map<String, Object>> list = new ArrayList();

        list.add(map);

        ApiResult<Object> result = new ApiResult();

        result.setC(resultEnum.getCode());
        result.setM(resultEnum.getMsg());
        result.setR(map);

        return result;
    }

    public static ApiResult success(ResultEnum resultEnum) {
        ApiResult<Object> result = new ApiResult();

        result.setC(resultEnum.getCode());
        result.setM(resultEnum.getMsg());
        return result;
    }

    public static ApiResult error(ResultEnum resultEnum) {
        ApiResult<Object> result = new ApiResult();

        result.setC(resultEnum.getCode());
        result.setM(resultEnum.getMsg());

        return result;
    }
}
