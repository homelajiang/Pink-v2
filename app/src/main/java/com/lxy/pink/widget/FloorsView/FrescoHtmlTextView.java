package com.lxy.pink.widget.FloorsView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class FrescoHtmlTextView extends android.support.v7.widget.AppCompatTextView {
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

    public void setListener(FrescoHtmlTextViewListener frescoHtmlTextViewListener) {
        this.mListener = frescoHtmlTextViewListener;
    }

    protected boolean verifyDrawable(Drawable drawable) {
        if (this.mListener == null || !this.mListener.onDetachedFromWindow(drawable)) {
            return super.verifyDrawable(drawable);
        }
        return true;
    }

    protected void onDetachedFromWindow() {
        if (this.mListener != null) {
            this.mListener.onDetachedFromWindow();
        }
        super.onDetachedFromWindow();
    }

    public void onStartTemporaryDetach() {
        if (this.mListener != null) {
            this.mListener.b();
        }
        super.onStartTemporaryDetach();
    }

    protected void onAttachedToWindow() {
        if (this.mListener != null) {
            this.mListener.c();
        }
        super.onAttachedToWindow();
    }

    public void onFinishTemporaryDetach() {
        if (this.mListener != null) {
            this.mListener.d();
        }
        super.onFinishTemporaryDetach();
    }
}