package com.lxy.pink.ui.video.models;

import android.view.View;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.acfun.ACAuth;
import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.ui.video.views.ACLoginView;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACLoginModel extends EpoxyModel<ACLoginView> {
    @EpoxyAttribute
    ACAuth acAuth;
    @EpoxyAttribute
    ACProfile acProfile;
    @EpoxyAttribute
    View.OnClickListener clickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.ac_login_model;
    }

    @Override
    public void bind(ACLoginView view) {

        if (acProfile == null) {
            view.setLevel("LV0");
            view.setBanana("0");
            view.setGoldBanana("0");
            if (acAuth == null) {
                view.setBtnLogin(R.string.login);
                view.setUsername("");
                view.setHeadIcon("res:///" + R.drawable.head_icon);
                view.setVipType(0);
            } else {
                view.setBtnLogin(R.string.check);
                view.setVipType(acAuth.getUserGroupLevel());
                view.setUsername(acAuth.getUsername());
                view.setHeadIcon(acAuth.getUserImg());
            }
        } else {
            if (acProfile.getStatus() == 200) {
                ACProfile.DataBean.FullUserBean fullUserBean = acProfile.getData().getFullUser();
                view.setBtnLogin(R.string.check);
                view.setLevel(String.valueOf("LV" + fullUserBean.getLevel()));
                view.setBanana(String.valueOf(fullUserBean.getBanana()));
                view.setGoldBanana(String.valueOf(fullUserBean.getBananaGold()));
                view.setUsername(fullUserBean.getUsername());
                view.setHeadIcon(fullUserBean.getUserImg());
            }
        }

        if (clickListener != null) {
            view.btnLogin.setOnClickListener(clickListener);
            view.headIcon.setOnClickListener(clickListener);
            view.msg.setOnClickListener(clickListener);
        }
    }

    @Override
    public boolean shouldSaveViewState() {
        return false;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
