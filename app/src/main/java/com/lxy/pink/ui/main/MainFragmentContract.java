package com.lxy.pink.ui.main;

import android.support.annotation.Nullable;

import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.ui.base.BasePresenter;
import com.lxy.pink.ui.base.BaseView;
import com.lxy.pink.player.PlayMode;
import com.lxy.pink.player.PlaybackService;

/**
 * Created by homelajiang on 2016/11/7 0007.
 */

public interface MainFragmentContract {


    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void handleError(Throwable e);

        void onPlaybackServiceBound(PlaybackService service);

        void onPlaybackServiceUnbound();

        void onSongUpdated(@Nullable Song song);

        void updatePlayMode(PlayMode playMode);

        void updatePlayToggle(boolean play);
    }

    interface Presenter extends BasePresenter {
        void retrieveLastPlayMode();

        void restoreLastSong();

        void bindPlaybackService();

        void unbindPlaybackService();
    }
}
