package com.lxy.pink.data.source;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.ui.player.PlayMode;
import com.lxy.pink.utils.Config;

import java.util.Date;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class PreferenceManager {

    private static final String KEY_FIRST_OPEN = "first_open";

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PRIVATEKEY = "privateKey";
    private static final String KEY_ACCESSTOKEN = "accessToken";
    private static final String KEY_PROFILEID = "profileId";
    private static final String KEY_NICKNAME = "nickname";
    private static final String KEY_USERIMG = "userImg";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_JOINDATE = "joinDate";
    private static final String KEY_QQ = "qq";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SIGNATURE = "signature";
    private static final String KEY_EXP = "exp";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_PLAY_MUSIC_MODE = "key_play_music_mode";

    private static Auth authBean;
    private static Profile profileBean;
    private static String cityIdString;

    private static SharedPreferences preferences(Context context) {
        return context.getSharedPreferences(Config.SP_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor edit(Context context) {
        return preferences(context).edit();
    }

    public static String getCityId(Context context) {
        if (TextUtils.isEmpty(cityIdString)) {
            cityIdString = preferences(context).getString("cityId", null);
        }
        return cityIdString;
    }

    public static void setCityId(Context context, String cityId) {
        cityIdString = cityId;
        edit(context).putString("cityId", cityId).apply();
    }


    public static boolean isFirstOpen(Context context) {
        return preferences(context).getBoolean(KEY_FIRST_OPEN, false);
    }

    public static void setFirstOpen(Context context) {
        edit(context).putBoolean(KEY_FIRST_OPEN, false).apply();
    }


    public static void setAuth(Context context, Auth auth) {
        authBean = auth;
        setA(context, auth);
    }

    public static Auth getAuth(Context context) {
        if (authBean == null) {
            authBean = getA(context);
        }
        return authBean;
    }

    public static void setProfile(Context context, Profile profile) {
        profileBean = profile;
        setP(context, profile);
    }

    public static Profile getProfile(Context context) {
        if (profileBean == null) {
            profileBean = getP(context);
        }
        return profileBean;
    }

    private static void setA(Context context, Auth auth) {
        SharedPreferences.Editor editor = edit(context);
        editor.putString(KEY_USERNAME, auth.getUsername());
        editor.putString(KEY_PRIVATEKEY, auth.getPrivateKey());
        editor.putString(KEY_ACCESSTOKEN, auth.getAccessToken());
        editor.putString(KEY_PROFILEID, auth.getProfileId());
        editor.apply();
    }

    private static Auth getA(Context context) {
        SharedPreferences sp = preferences(context);
        Auth auth = new Auth();
        auth.setUsername(sp.getString(KEY_USERNAME, null));
        auth.setPrivateKey(sp.getString(KEY_PRIVATEKEY, null));
        auth.setAccessToken(sp.getString(KEY_ACCESSTOKEN, null));
        auth.setProfileId(sp.getString(KEY_PROFILEID, null));
        if (TextUtils.isEmpty(auth.getUsername()) || TextUtils.isEmpty(auth.getPrivateKey()) ||
                TextUtils.isEmpty(auth.getAccessToken()) || TextUtils.isEmpty(auth.getProfileId())) {
            return null;
        }
        return auth;
    }

    private static void setP(Context context, Profile profile) {
        SharedPreferences.Editor editor = edit(context);
        editor.putString(KEY_NICKNAME, profile.getNickname());
        editor.putString(KEY_USERIMG, profile.getUserImg());
        editor.putInt(KEY_GENDER, profile.getGender());
        editor.putLong(KEY_JOINDATE, profile.getJoinDate().getTime());
        editor.putString(KEY_QQ, profile.getQq());
        editor.putString(KEY_EMAIL, profile.getEmail());
        editor.putString(KEY_SIGNATURE, profile.getSignature());
        editor.putInt(KEY_EXP, profile.getExp());
        editor.putInt(KEY_LEVEL, profile.getLevel());
        editor.putString(KEY_MOBILE, profile.getMobile());
        editor.apply();
    }

    private static Profile getP(Context context) {
        SharedPreferences sp = preferences(context);
        Profile profile = new Profile();
        profile.setNickname(sp.getString(KEY_NICKNAME, null));
        if (TextUtils.isEmpty(profile.getNickname())) {
            return null;
        }
        profile.setUserImg(sp.getString(KEY_USERIMG, null));
        profile.setGender(sp.getInt(KEY_GENDER, 0));
        profile.setJoinDate(new Date(sp.getLong(KEY_JOINDATE, 0)));
        profile.setQq(sp.getString(KEY_QQ, null));
        profile.setEmail(sp.getString(KEY_EMAIL, null));
        profile.setSignature(sp.getString(KEY_SIGNATURE, null));
        profile.setExp(sp.getInt(KEY_EXP, 0));
        profile.setLevel(sp.getInt(KEY_LEVEL, 0));
        profile.setMobile(sp.getString(KEY_MOBILE, null));
        return profile;
    }

    public static PlayMode lastPlayMode(Context context) {
        String playModeName = preferences(context).getString(KEY_PLAY_MUSIC_MODE, null);
        if (playModeName != null) {
            return PlayMode.valueOf(playModeName);
        }
        return PlayMode.getDefault();
    }
    public static void setPlayMode(Context context, PlayMode playMode) {
        edit(context).putString(KEY_PLAY_MUSIC_MODE, playMode.name()).commit();
    }
}
