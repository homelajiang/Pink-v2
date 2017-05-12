//package com.lxy.pink.widget.FloorsView;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.drawable.Animatable;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.graphics.drawable.Drawable.Callback;
//import android.net.Uri;
//import android.text.Html.ImageGetter;
//import android.text.TextUtils;
//import android.widget.TextView;
//
//import com.facebook.drawee.backends.pipeline.Fresco;
//import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
//import com.facebook.drawee.controller.ControllerListener;
//import com.facebook.drawee.generic.GenericDraweeHierarchy;
//import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
//import com.facebook.drawee.view.DraweeHolder;
//import com.facebook.drawee.view.MultiDraweeHolder;
//import com.facebook.imagepipeline.image.ImageInfo;
//
//import java.lang.ref.WeakReference;
//
//import pl.droidsonroids.gif.GifDrawable;
//import tv.acfun.core.control.helper.LogHelper;
//import tv.acfun.core.control.util.DeviceUtil;
//import tv.acfun.core.control.util.UnitUtil;
//import tv.acfun.core.control.util.Utils;
//import tv.acfun.core.model.Constants;
//
///* compiled from: unknown */
//public class FrescoImageGetter implements Callback, ImageGetter, FrescoHtmlTextViewListener {
//    private String a = "FrescoImageGetter";
//    private Context b;
//    private WeakReference<TextView> c;
//    private MultiDraweeHolder<GenericDraweeHierarchy> d;
//    private int e;
//
//    /* compiled from: unknown */
//    private static final class URLDrawable extends BitmapDrawable {
//        private Drawable a;
//
//        public void draw(Canvas canvas) {
//            if (this.a != null) {
//                this.a.draw(canvas);
//            }
//        }
//
//        public void a(Drawable drawable) {
//            this.a = drawable;
//        }
//
//        public Drawable a() {
//            return this.a;
//        }
//    }
//
//    public FrescoImageGetter(Context context) {
//        this.b = context;
//        this.d = new MultiDraweeHolder();
//        this.e = (int) (((float) DeviceUtil.d(context)) * 0.8f);
//    }
//
//    public void a(TextView textView) {
//        this.c = new WeakReference(textView);
//    }
//
//    public void invalidateDrawable(Drawable drawable) {
//        if (this.c != null && this.c.get() != null) {
//            ((TextView) this.c.get()).invalidate();
//        }
//    }
//
//    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
//        if (this.c != null && this.c.get() != null) {
//            ((TextView) this.c.get()).postDelayed(runnable, j);
//        }
//    }
//
//    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
//        if (this.c != null && this.c.get() != null) {
//            ((TextView) this.c.get()).removeCallbacks(runnable);
//        }
//    }
//
//    public Drawable getDrawable(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        ((FrescoHtmlTextView) this.c.get()).setListener(this);
//        if (str.startsWith("http") || str.startsWith("https")) {
//            return a(str);
//        }
//        return c(str);
//    }
//
//    private Drawable c(String str) {
//        Drawable drawable = null;
//        try {
//            String b = b(str + Constants.EMOTION_FILE_SUFFIX);
//            if (!TextUtils.isEmpty(b)) {
//                if (b.contains("ac3/") || b.contains("brd/") || b.contains("td/") || b.contains("tsj/")) {
//                    Drawable gifDrawable = new GifDrawable(this.b.getAssets(), b);
//                    try {
//                        gifDrawable.setCallback(this);
//                        drawable = gifDrawable;
//                    } catch (Exception e) {
//                        Exception exception = e;
//                        drawable = gifDrawable;
//                        Exception exception2 = exception;
//                        exception2.printStackTrace();
//                        return drawable;
//                    }
//                }
//                drawable = Drawable.createFromStream(this.b.getAssets().open(b), b);
//                drawable.setBounds(0, 0, UnitUtil.a(this.b, 48.0f), UnitUtil.a(this.b, 48.0f));
//            }
//        } catch (Exception e2) {
//            exception2 = e2;
//            exception2.printStackTrace();
//            return drawable;
//        }
//        return drawable;
//    }
//
//    public Drawable a(String str) {
//        LogHelper.b(this.a, "loadUrlDrawable:" + str);
//        final Drawable uRLDrawable = new URLDrawable();
//        final DraweeHolder draweeHolder = new DraweeHolder(new GenericDraweeHierarchyBuilder(this.b.getResources()).build());
//        this.d.add(draweeHolder);
//        draweeHolder.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(Uri.parse(str)).setOldController(draweeHolder.getController())).setControllerListener(new ControllerListener<ImageInfo>(this) {
//            final /* synthetic */ FrescoImageGetter c;
//
//            public /* synthetic */ void onFinalImageSet(String str, Object obj, Animatable animatable) {
//                a(str, (ImageInfo) obj, animatable);
//            }
//
//            public /* synthetic */ void onIntermediateImageSet(String str, Object obj) {
//                a(str, (ImageInfo) obj);
//            }
//
//            public void onSubmit(String str, Object obj) {
//                LogHelper.b(this.c.a, "onSubmit");
//            }
//
//            public void a(String str, ImageInfo imageInfo, Animatable animatable) {
//                LogHelper.b(this.c.a, "onFinalImageSet  TextViewWidth=" + ((TextView) this.c.c.get()).getWidth() + "  width=" + imageInfo.getWidth() + "  Height=" + imageInfo.getHeight() + " Animatable=" + animatable);
//                Drawable topLevelDrawable = draweeHolder.getHierarchy().getTopLevelDrawable();
//                int width = imageInfo.getWidth();
//                int height = imageInfo.getHeight();
//                if (width > this.c.e) {
//                    height = (int) ((((double) height) / ((double) width)) * ((double) this.c.e));
//                    width = this.c.e;
//                }
//                topLevelDrawable.setBounds(0, 0, width, height);
//                uRLDrawable.setBounds(0, 0, width, height);
//                uRLDrawable.a(topLevelDrawable);
//                TextView textView = (TextView) this.c.c.get();
//                if (textView != null) {
//                    textView.setText(textView.getText());
//                }
//                textView.postInvalidateDelayed(500);
//            }
//
//            public void a(String str, ImageInfo imageInfo) {
//                LogHelper.b(this.c.a, "onIntermediateImageSet width:" + imageInfo.getWidth() + ",height:" + imageInfo.getHeight());
//            }
//
//            public void onIntermediateImageFailed(String str, Throwable th) {
//            }
//
//            public void onFailure(String str, Throwable th) {
//            }
//
//            public void onRelease(String str) {
//                LogHelper.b(this.c.a, "onSubmit");
//            }
//        })).build());
//        return uRLDrawable;
//    }
//
//    public String b(String str) {
//        if (TextUtils.isEmpty(str) || !str.contains(",")) {
//            return "";
//        }
//        String[] split = str.split(",");
//        String str2 = split[0] + "/" + split[1];
//        if (Utils.c()) {
//            str2 = "AprilFool/" + str2;
//        }
//        LogHelper.b(this.a, "file=" + str2);
//        return str2;
//    }
//
//    public boolean a(Drawable drawable) {
//        if ((drawable instanceof URLDrawable) && this.d.verifyDrawable(((URLDrawable) drawable).a())) {
//            return true;
//        }
//        return false;
//    }
//
//    public void a() {
//        this.d.onDetach();
//    }
//
//    public void b() {
//        this.d.onDetach();
//    }
//
//    public void c() {
//        if (this.c != null) {
//            ((TextView) this.c.get()).postInvalidate();
//        }
//        this.d.onAttach();
//    }
//
//    public void d() {
//        this.d.onAttach();
//    }
//}