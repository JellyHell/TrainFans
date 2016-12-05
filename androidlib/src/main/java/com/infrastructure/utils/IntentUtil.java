package com.infrastructure.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/11/21.
 */

public class IntentUtil {
    public static void forwordToActivity(Context context, Class<?> cls) {
        Intent aIntent = new Intent(context, cls);
        context.startActivity(aIntent);
    }


}

