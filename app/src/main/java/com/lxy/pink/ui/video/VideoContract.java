package com.lxy.pink.ui.video;

import com.lxy.pink.data.model.acfun.ACAuthRes;
import com.lxy.pink.data.model.acfun.ACBaseModel;
import com.lxy.pink.data.model.acfun.ACProfile;
import com.lxy.pink.data.model.acfun.ACRecommend;
import com.lxy.pink.data.model.acfun.ACSign;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by yuan on 2016/12/21.
 */

public class VideoContract {
    interface View extends BaseView<Presenter> {
        void recommendLoaded(ACRecommend acRecommend);

        void recommendError(Throwable e);

        void loginStart();

        void loginLoaded(ACAuthRes acAuthRes);

        void loginError(Throwable e);

        void acProfileLoaded(ACProfile acProfile);

        void checkSignLoaded(ACBaseModel signRes);

        void signLoaded(ACSign acSign);

        void signError(Throwable e);
    }

    interface Presenter extends BasePresenter {
        void getRecommend();

        void login(String username, String password);

        void getProfile(String uid);

        void checkSign(String accessToken);

        void sign(String accessToken);
    }
}
