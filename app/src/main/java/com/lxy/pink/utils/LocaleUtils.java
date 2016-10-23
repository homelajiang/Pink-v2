package com.lxy.pink.utils;

import java.util.Locale;

/**
 * Created by yuan on 2016/10/23.
 */

public class LocaleUtils {

    private static Locale locale;

    public static Locale getLocale() {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return locale;
    }
}
