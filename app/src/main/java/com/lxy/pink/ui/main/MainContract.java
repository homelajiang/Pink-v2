package com.lxy.pink.ui.main;

import com.lxy.pink.data.model.auth.Profile;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

/**
 * Created by yuan on 2016/10/23.
 */

public class MainContract {
    interface View extends BaseView<Presenter> {

        void profileLoad(Profile profile);

    }

    interface Presenter extends BasePresenter {
        void getProfile(String profileId);
    }
}
