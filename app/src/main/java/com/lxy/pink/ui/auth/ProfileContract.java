package com.lxy.pink.ui.auth;

import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by homelajiang on 2016/10/19 0019.
 */

public interface ProfileContract {

    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void profileLoad(Profile profile);

        void profileUploaded(Profile profile);
    }

    interface Presenter extends BasePresenter {
        void getProfile();

        void updateProfile(Profile profile);
    }
}