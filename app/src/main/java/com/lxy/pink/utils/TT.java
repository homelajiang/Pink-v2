package com.lxy.pink.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by homelajiang on 2016/10/19 0019.
 */

public class TT {
    public static void s(Context context, String msg){
        Toast.makeText(context,String.valueOf(msg),Toast.LENGTH_SHORT).show();
    }

    public static void s(Context context, @StringRes int ResId){
        Toast.makeText(context,context.getString(ResId),Toast.LENGTH_SHORT).show();
    }
}
