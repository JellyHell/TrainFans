package com.peixunfan.trainfans.Utils.SPManager;

import android.content.Context;

import com.infrastructure.utils.PreferencesUtil;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class UserInfoMangager {
    public static final String USER_INFO = "userinfo_propertiesInfo";

    public static PreferencesUtil mInfoUtil = null;

    /**
     * Preferences文件
     */
    private static PreferencesUtil getPreferencesUtil(Context context) {
        if (mInfoUtil == null) {
            mInfoUtil = new PreferencesUtil(context, USER_INFO);
        }
        return mInfoUtil;
    }

    /** 登陆*/
    public static void saveLoginState(Context context,boolean isLoged){
        mInfoUtil = getPreferencesUtil(context);
        mInfoUtil.putBoolean("login_state",isLoged);
    }

    public static boolean isLogined(Context context){
        mInfoUtil = getPreferencesUtil(context);
        return mInfoUtil.getBooleanDefaultFalse("login_state");
    }
}
