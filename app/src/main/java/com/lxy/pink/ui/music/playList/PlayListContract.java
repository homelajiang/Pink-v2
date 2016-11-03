package com.lxy.pink.ui.music.playList;

import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;

import java.util.List;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public interface PlayListContract {

    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void handleError(Throwable e);

        void onPlayListLoaded(List<PlayList> playLists);

        void onPlayListCreate(PlayList playList);

        void onPlayListEdited(PlayList playList);

        void onPlayListDeleted(PlayList playList);
    }

    interface Presenter extends BasePresenter {
        void loadPlayList();

        void createPlatList(PlayList playList);

        void editPlayList(PlayList playList);

        void deletePlayList(PlayList playList);
    }
}
