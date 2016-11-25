package com.infrastructure.utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/11/10.
 */

public class StringUtil {
    /** 获取标准两位小数  Float*/
    public static String getFormatedFloatString(String originalString) {
        try {
            float amount = Float.parseFloat(originalString);
            DecimalFormat df = new DecimalFormat("##0.00");
            return df.format(amount);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "0.00";
    }
}
