package com.lxy.pink.utils;

import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.BuildConfig;
import com.lxy.pink.R;

/**
 * Created by homelajiang on 2016/12/22 0022.
 */

public class FrescoUtils {
    public static void setImage(String imgUrl, SimpleDraweeView simpleDraweeView) {
        if (simpleDraweeView == null)
            return;
        if (!TextUtils.isEmpty(imgUrl) && imgUrl.endsWith(".gif")) {
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(imgUrl))
                    .setAutoPlayAnimations(true)
                    .build();
            simpleDraweeView.setController(controller);
        } else if (TextUtils.isEmpty(imgUrl)) {
            simpleDraweeView.setImageURI(Uri.parse("res://com.lxy.pink/"+R.drawable.ac_head_default));
        } else {
            simpleDraweeView.setImageURI(Uri.parse(imgUrl));
        }
    }
}
