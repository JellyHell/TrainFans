package com.peixunfan.trainfans.Base;

import com.google.gson.annotations.SerializedName;
import com.infrastructure.utils.TextUtil;

/**
 * Created by Administrator on 2016/11/8.
 */

public class BaseResponse {
    @SerializedName("ret_code") public String ret_code;
    @SerializedName("ret_desc") public String ret_desc;

    public boolean isSuccess() {
        if (!TextUtil.isEmpty(ret_code) && ret_code.equals("000000")) {
            return true;
        } else {
            return false;
        }
    }
}
