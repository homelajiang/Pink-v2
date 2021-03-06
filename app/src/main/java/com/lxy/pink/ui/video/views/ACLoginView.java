package com.lxy.pink.ui.video.views;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxy.pink.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACLoginView extends CardView {
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.msg)
    public ImageView msg;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.bananaGold)
    TextView bananaGold;
    @BindView(R.id.banana)
    TextView banana;
    @BindView(R.id.head_icon)
    public SimpleDraweeView headIcon;
    @BindView(R.id.btn_login)
    public AppCompatImageView btnLogin;

    public ACLoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.ac_login_view, this);
        ButterKnife.bind(this);
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public void setBtnLogin(int btnLoginRes) {
    }

    public void setLevel(String level) {
        this.level.setText(String.valueOf(level));
    }

    public void setBanana(String banana) {
        this.banana.setText(String.valueOf(banana));
    }

    public void setGoldBanana(String banana) {
        this.bananaGold.setText(String.valueOf(banana));
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon.setImageURI(Uri.parse(headIcon));
    }

    public void setVipType(int userGroupLevel) {
    }

}
