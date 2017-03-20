package com.lxy.pink.utils;

/**
 * Created by yuan on 2017/3/20.
 */

public class AcCountUtils {

    public static String[] formatVideoCount(final int counts){
        String[] count = new String[2];

       if(counts<=0){
           count[0] = "0";
           count[1] = null;
       }else if(counts<1000){
           count[0] = String.valueOf(counts);
           count[1] = null;
       }else if(counts<10000){
           count[0] = String.valueOf((int)(counts/1000));
           count[1] = "千";
       }else if(counts<100000000){
           count[0] = String.valueOf((int)(counts/10000));
           count[1] = "万";
       }else if(counts<100000000){
           count[0] = String.valueOf((int)(counts/100000000));
           count[1] = "亿";
       }else {
           count[0] = String.valueOf("+∞");
           count[1] = null;
       }


        return count;
    }
}
