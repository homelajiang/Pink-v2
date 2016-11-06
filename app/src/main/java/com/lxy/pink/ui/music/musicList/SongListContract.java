package com.lxy.pink.ui.music.musicList;

import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

import java.util.List;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public interface SongListContract {

    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void handleError(Throwable e);

        void onMusicListLoaded(PlayList playList);
    }

    interface Presenter extends BasePresenter {
        void loadMusicList(List<String> filters);
    }
}
