package com.lxy.pink.ui.video;

import com.lxy.pink.data.model.acfun.ACAuthRes;
import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by yuan on 2016/12/21.
 */

public class VideoContract {
    interface View extends BaseView<Presenter> {
        void recommendLoad(ACRecommend acRecommend);

        void recommendError(Throwable e);

        void loginStart();

        void loginSuccess(ACAuthRes acAuthRes);

        void loginError(Throwable e);

        void acProfileLoad(ACProfile acProfile);
    }

    interface Presenter extends BasePresenter {
        void getRecommend();

        void login(String username, String password);

        void getProfile(String uid);
    }
}
