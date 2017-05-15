package com.lxy.pink.utils;

import android.text.TextUtils;

import com.lxy.pink.Injection;
import com.lxy.pink.R;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class UBBUtil {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return Pattern.compile("<br/>(<br/>)+", CASE_INSENSITIVE).matcher(str).replaceAll("<br/>").replaceAll("\\[(b|/b)\\]+?", "<$1>").replaceAll("\\[(s|/s)\\]+?", "<$1>").replaceAll("\\[(i|/i)\\]+?", "").replaceAll("\\[(u|/u)\\]+?", "<$1>").replaceAll("\\[(align=[\\S\\s]+?|/align)\\]", "").replaceAll("\\[email[\\S\\s]+?/email\\]", "").replaceAll("\\[img=[\\S\\s]+?\\]([\\S\\s]+?)\\[/img\\]", " <img src='$1'/> ").replace("[at]", "@").replace("[/at]", "").replaceAll("\\[ac=[\\S\\s]+?\\]([\\S\\s]+?)\\[/ac\\]", "$1").replaceAll("\\[font=[\\S\\s]+?\\]([\\S\\s]+?)\\[/font\\]", "$1").replaceAll("\\[back=[\\S\\s]+?\\]([\\S\\s]+?)\\[/back\\]", "$1").replaceAll("\\[emot=([\\S\\s]+?)/\\]", " <img src='$1'/> ").replaceAll("\\[url\\]([\\S\\s]+?)\\[/url\\]", "<onDetachedFromWindow href='$1'>$1</onDetachedFromWindow>").replaceAll("\\[color=([\\S\\s]+?)\\]", "<font color='$1'>").replace("[/color]", "</font>").replaceAll("\\[size=([\\S\\s]+?)px\\]", "<size='$1'>").replace("[/size]", "</size>");
    }

    public static String b(String str) {
        return str.replaceAll("\\[emot=([\\S\\s]+?)/\\]", "(" + Injection.provideContext().getString(R.string.emoji) + ")").replaceAll("\\[url\\]([\\S\\s]+?)\\[/url\\]", "$1").replaceAll("\\[img=([\\S\\s]+?)/\\]", "(" + Injection.provideContext().getString(R.string.image) + ")").replaceAll("\\[([\\S\\s]+?)\\]([\\S\\s]??)", "");
    }

    public static String c(String str) {
        return str.replaceAll("\\[emot=ac,([\\S\\s]+?)/\\]", "");
    }
}