package com.hdsx.appservice.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 梁铖城 2019年03月25日13:55:26
 */
public class XzqhUtils {

    private static final String ALL = "000000";
    private static final String PROVINCE = "0000";
    private static final String CITY = "00";

    private static final int START = 0;
    private static final int PROVINCE_END = 2;
    private static final int CITY_END = 4;

    public static String getXzqhCause(String xzqhcode) {
        String xzqh = "";
        if (!StringUtils.isEmpty(xzqhcode) && !ALL.equals(xzqhcode)) {
            //省
            if (PROVINCE.equals(xzqhcode.substring(PROVINCE_END))) {
                xzqh = xzqhcode.substring(START, PROVINCE_END);
            }
            //市
            else if (CITY.equals(xzqhcode.substring(CITY_END))) {
                xzqh = xzqhcode.substring(START, CITY_END);
            }
            //县
            else {
                xzqh = xzqhcode;
            }
        }
        return xzqh;
    }
}
