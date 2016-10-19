package com.lxy.pink.ui.auth;

import com.lxy.pink.data.model.auth.Auth;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by homelajiang on 2016/10/19 0019.
 */

public interface SignContract {

    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void onSignUp(Auth auth);

        void handleError(Throwable error);

        void onSignIn(Auth auth);

    }

    interface Presenter extends BasePresenter {

        void signUp(String username, String password);

        void signIn(String username, String password);
    }


}
