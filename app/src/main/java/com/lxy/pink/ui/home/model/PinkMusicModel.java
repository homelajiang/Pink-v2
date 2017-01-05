package com.lxy.pink.ui.home.model;

import android.os.Handler;
import android.support.annotation.Nullable;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.player.IPlayback;
import com.lxy.pink.player.PlaybackService;
import com.lxy.pink.ui.home.view.PinkMusicView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkMusicModel extends EpoxyModel<PinkMusicView> implements IPlayback.Callback {

    @EpoxyAttribute
    PlaybackService mPlayer;

    private PinkMusicView musicView;

    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_music_model;
    }

    @Override
    public void bind(PinkMusicView view) {
        this.musicView = view;
        musicView.ABind(mPlayer);
    }

    @Override
    public void unbind(PinkMusicView view) {
        super.unbind(view);
        musicView.unABind();
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void onSwitchLast(@Nullable Song last) {
        musicView.updateUI(last);
    }

    @Override
    public void onSwitchNext(@Nullable Song next) {
        musicView.updateUI(next);
    }

    @Override
    public void onComplete(@Nullable Song next) {
        musicView.updateUI(next);
    }

    @Override
    public void onPlayStatusChanged(boolean isPlaying) {
        musicView.updatePlayToggle(isPlaying);
    }
}
