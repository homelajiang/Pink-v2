package com.lxy.pink.widget.FloorsView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import tv.acfun.core.control.helper.LogHelper;

/* compiled from: unknown */
public class FrescoHtmlTextView extends TextView {
    private String TAG = "FrescoHtmlTextView";
    private FrescoHtmlTextViewListener mListener;

    public FrescoHtmlTextView(Context context) {
        super(context);
    }

    public FrescoHtmlTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FrescoHtmlTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public FrescoHtmlTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setListener(FrescoHtmlTextViewListener frescoHtmlTextViewListener) {
        this.mListener = frescoHtmlTextViewListener;
    }

    protected boolean verifyDrawable(Drawable drawable) {
        LogHelper.b(this.TAG, "verifyDrawable");
        if (this.mListener == null || !this.mListener.a(drawable)) {
            return super.verifyDrawable(drawable);
        }
        return true;
    }

    protected void onDetachedFromWindow() {
        if (this.mListener != null) {
            LogHelper.b(this.TAG, "onDetachedFromWindow");
            this.mListener.a();
        }
        super.onDetachedFromWindow();
    }

    public void onStartTemporaryDetach() {
        if (this.mListener != null) {
            LogHelper.b(this.TAG, "onStartTemporaryDetach");
            this.mListener.b();
        }
        super.onStartTemporaryDetach();
    }

    protected void onAttachedToWindow() {
        if (this.mListener != null) {
            LogHelper.b(this.TAG, "onAttachedToWindow");
            this.mListener.c();
        }
        super.onAttachedToWindow();
    }

    public void onFinishTemporaryDetach() {
        if (this.mListener != null) {
            LogHelper.b(this.TAG, "onFinishTemporaryDetach");
            this.mListener.d();
        }
        super.onFinishTemporaryDetach();
    }
}