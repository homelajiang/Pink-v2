package com.lxy.pink.widget.FloorsView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.net.Uri;
import android.text.Html.ImageGetter;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.MultiDraweeHolder;
import com.facebook.imagepipeline.image.ImageInfo;

import java.lang.ref.WeakReference;

import pl.droidsonroids.gif.GifDrawable;

/* compiled from: unknown */
public class FrescoImageGetter implements Callback, ImageGetter, FrescoHtmlTextViewListener {
    private Context context;
    private WeakReference<TextView> mWeakReference;
    private MultiDraweeHolder<GenericDraweeHierarchy> mMultiDraweeHolder;
    private int MIN_SIZE;

    /* compiled from: unknown */
    private static final class URLDrawable extends BitmapDrawable {
        private Drawable a;

        public void draw(Canvas canvas) {
            if (this.a != null) {
                this.a.draw(canvas);
            }
        }

        public void setDrawable(Drawable drawable) {
            this.a = drawable;
        }

        public Drawable a() {
            return this.a;
        }
    }

    public FrescoImageGetter(Context context) {
        this.context = context;
        this.mMultiDraweeHolder = new MultiDraweeHolder();
        this.MIN_SIZE = (int) ((d(context)) * 0.8f);
    }

    public void putTextView(TextView textView) {
        this.mWeakReference = new WeakReference(textView);
    }

    public int d(Context context) {
        int i;
        synchronized (FrescoImageGetter.class) {
            Point point = new Point();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
            i = point.x;
        }
        return i;
    }


    public void invalidateDrawable(Drawable drawable) {
        if (this.mWeakReference != null && this.mWeakReference.get() != null) {
            ((TextView) this.mWeakReference.get()).invalidate();
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (this.mWeakReference != null && this.mWeakReference.get() != null) {
            ((TextView) this.mWeakReference.get()).postDelayed(runnable, j);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (this.mWeakReference != null && this.mWeakReference.get() != null) {
            ((TextView) this.mWeakReference.get()).removeCallbacks(runnable);
        }
    }

    @Override
    public Drawable getDrawable(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ((FrescoHtmlTextView) this.mWeakReference.get()).setListener(this);
        if (str.startsWith("http") || str.startsWith("https")) {
            return getNetDrawable(str);
        }
        return getEmojiDrawable(str);
    }

    private Drawable getEmojiDrawable(String str) {
        Drawable drawable = null;
        try {
            String emojiDrawable = eggs(str + ".gif");
            if (!TextUtils.isEmpty(emojiDrawable)) {
                if (emojiDrawable.contains("ac3/") || emojiDrawable.contains("brd/")
                        || emojiDrawable.contains("td/") || emojiDrawable.contains("tsj/")) {
                    Drawable gifDrawable = new GifDrawable(this.context.getAssets(), emojiDrawable);
                    try {
                        gifDrawable.setCallback(this);
                        drawable = gifDrawable;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return drawable;
                    }
                }else {
                    drawable = Drawable.createFromStream(this.context.getAssets().open(emojiDrawable), emojiDrawable);
                }
                int bound = Math.round(context.getResources().getDisplayMetrics().density * 48.0f);
                drawable.setBounds(0, 0, bound, bound);
            }
        } catch (Exception e2) {
            return drawable;
        }
        return drawable;
    }

    public Drawable getNetDrawable(String str) {
        final URLDrawable uRLDrawable = new URLDrawable();
        final DraweeHolder draweeHolder = new DraweeHolder(new GenericDraweeHierarchyBuilder(this.context.getResources()).build());
        this.mMultiDraweeHolder.add(draweeHolder);
        draweeHolder.setController((Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(str)).setOldController(draweeHolder.getController())
                .setControllerListener(
                        new ControllerListener<ImageInfo>() {
                            @Override
                            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                            }

                            @Override
                            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                                Drawable topLevelDrawable = draweeHolder.getHierarchy().getTopLevelDrawable();
                                int width = imageInfo.getWidth();
                                int height = imageInfo.getHeight();
                                if (width > MIN_SIZE) {
                                    height = (int) ((((double) height) / ((double) width)) * ((double) MIN_SIZE));
                                    width = MIN_SIZE;
                                }
                                topLevelDrawable.setBounds(0, 0, width, height);
                                uRLDrawable.setBounds(0, 0, width, height);
                                uRLDrawable.setDrawable(topLevelDrawable);
                                TextView textView = mWeakReference.get();
                                if (textView != null) {
                                    textView.setText(textView.getText());
                                }
                                textView.postInvalidateDelayed(500);
                            }

                            public void onSubmit(String str, Object obj) {
                            }

                            public void onIntermediateImageFailed(String str, Throwable th) {
                            }

                            public void onFailure(String str, Throwable th) {
                            }

                            public void onRelease(String str) {
                            }
                        })).build());
        return uRLDrawable;
    }

    public String eggs(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(",")) {
            return "";
        }
        String[] split = str.split(",");
        String str2 = split[0] + "/" + split[1];
        if (false) {
            str2 = "AprilFool/" + str2;
        }
        return str2;
    }

    public boolean onDetachedFromWindow(Drawable drawable) {
        if ((drawable instanceof URLDrawable) && this.mMultiDraweeHolder.verifyDrawable(((URLDrawable) drawable).a())) {
            return true;
        }
        return false;
    }

    public void onDetachedFromWindow() {
        this.mMultiDraweeHolder.onDetach();
    }

    public void b() {
        this.mMultiDraweeHolder.onDetach();
    }

    public void c() {
        if (this.mWeakReference != null) {
            ((TextView) this.mWeakReference.get()).postInvalidate();
        }
        this.mMultiDraweeHolder.onAttach();
    }

    public void d() {
        this.mMultiDraweeHolder.onAttach();
    }
}