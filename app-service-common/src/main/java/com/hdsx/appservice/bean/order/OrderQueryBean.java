package com.hdsx.appservice.bean.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 梁铖城
 * 2020年03月03日10:20:03
 * 订单查询的体对象
 */
@ApiModel
public class OrderQueryBean implements Serializable {

    @ApiModelProperty("订单状态")
    private int orderstate;

    @ApiModelProperty("购买者ID")
    private String userid;

    @ApiModelProperty("分页条数")
    private Integer limit;

    @ApiModelProperty("当前页")
    private Integer current;

    public int getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(int orderstate) {
        this.orderstate = orderstate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }
}
