package com.lxy.pink.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;

import com.lxy.pink.R;
import com.lxy.pink.widget.CharacterDrawable;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public class ViewUtils {
    public static void setLightStatusBar(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static void clearLightStatusBar(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static CharacterDrawable generateAlbumDrawable(Context context, String albumName) {
        if (context == null || albumName == null) return null;

        return new CharacterDrawable.Builder()
                .setCharacter(albumName.length() == 0 ? ' ' : albumName.charAt(0))
//                .setBackgroundColor(ContextCompat.getColor(context, R.color.mp_characterView_background))
//                .setCharacterTextColor(ContextCompat.getColor(context, R.color.mp_characterView_textColor))
                .setCharacterPadding(context.getResources().getDimensionPixelSize(R.dimen.mp_characterView_padding))
                .build();
    }

}
