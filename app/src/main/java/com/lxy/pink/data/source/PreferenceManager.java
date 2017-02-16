package com.lxy.pink.data.source;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.lxy.pink.data.model.acfun.ACAuth;
import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.player.PlayMode;
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
    private static final String KEY_PLAY_MUSIC_MODE = "key_play_music_mode";
    private static final String KEY_LAST_SONG = "key_last_song";

    private static Auth authBean;

    private static ACAuth acAuth;

    private static SharedPreferences preferences(Context context) {
        return context.getSharedPreferences(Config.SP_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor edit(Context context) {
        return preferences(context).edit();
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

    public static ACAuth getAcAuth(Context context) {
        if (acAuth == null)
            acAuth = getAcA(context);
        return acAuth;
    }

    public static void setAcAuth(Context context, ACAuth acAuth) {
        PreferenceManager.acAuth = acAuth;
        setAcA(context, acAuth);
    }

    private static void setAcA(Context context, ACAuth acAuth) {
        SharedPreferences.Editor editor = edit(context);
        editor.putString("ac_username", acAuth.getUsername());
        editor.putString("ac_access_token", acAuth.getAccess_token());
        editor.putString("ac_userImg", acAuth.getUserImg());
        editor.putInt("ac_userGroupLevel", acAuth.getUserGroupLevel());
        editor.putInt("ac_mobileCheck", acAuth.getMobileCheck());
        editor.putLong("ac_expires", acAuth.getExpires());
        editor.putLong("ac_userId", acAuth.getUserId());
        editor.apply();
    }

    private static ACAuth getAcA(Context context) {
        SharedPreferences sp = preferences(context);
        ACAuth acAuth = new ACAuth();
        acAuth.setUsername(sp.getString("ac_username", null));
        acAuth.setAccess_token(sp.getString("ac_access_token", null));
        acAuth.setUserImg(sp.getString("ac_userImg", null));
        acAuth.setUserGroupLevel(sp.getInt("ac_userGroupLevel", 0));
        acAuth.setMobileCheck(sp.getInt("ac_mobileCheck", 0));
        acAuth.setExpires(sp.getLong("ac_expires", 0));
        acAuth.setUserId(sp.getLong("ac_userId", 0));

        if (TextUtils.isEmpty(acAuth.getUsername())
                || TextUtils.isEmpty(acAuth.getAccess_token())
                || acAuth.getUserId() == 0)
            return null;
        return acAuth;
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


    public static PlayMode getPlayMode(Context context) {
        String playModeName = preferences(context).getString(KEY_PLAY_MUSIC_MODE, null);
        if (playModeName != null) {
            return PlayMode.valueOf(playModeName);
        }
        return PlayMode.getDefault();
    }

    public static void setPlayMode(Context context, PlayMode playMode) {
        edit(context).putString(KEY_PLAY_MUSIC_MODE, playMode.name()).commit();
    }

    public static void setLastSong(Context context, long id) {
        edit(context).putLong(KEY_LAST_SONG, id).apply();
    }

    public static long getLastSong(Context context) {
        return preferences(context).getLong(KEY_LAST_SONG, 0);
    }
}
