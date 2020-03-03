package com.hdsx.appservice.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 梁铖城
 * 2018年04月27日11:25:16
 * 返回体 - 暂不用
 */
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 码
     */
    @SerializedName("code")
    private int c = 0;

    /**
     * 信息
     */
    @SerializedName("message")
    private String m = "";

    public Map<String, Object> getR() {
        return r;
    }

    public void setR(Map<String, Object> r) {
        this.r = r;
    }

    /**
     * 返回值
     */
    @SerializedName("result")
    private Map<String,Object> r = new HashMap();

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "c='" + c + '\'' +
                ", m='" + m + '\'' +
                ", r=" + r +
                '}';
    }
}