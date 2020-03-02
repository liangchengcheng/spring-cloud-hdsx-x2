package com.hdsx.appservice.utils;

public class XzqhUtil {


    public static String convertXzqh(String code) {
        if (code != null && !code.equals("")) {
            if (code.startsWith("11") || code.startsWith("12") || code.startsWith("31") || code.startsWith("50")) {
                return code.substring(0, 2) + "0100";
            } else {
                return code.substring(0, 4) + "00";
            }
        } else {
            return "";
        }
    }
}
