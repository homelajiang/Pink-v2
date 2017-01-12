package com.lxy.pink.ui.home.model;

import android.support.annotation.Nullable;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.player.IPlayback;
import com.lxy.pink.player.PlaybackService;
import com.lxy.pink.ui.home.view.PinkMusicView;
import com.orhanobut.logger.Logger;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkMusicModel extends EpoxyModel<PinkMusicView> implements IPlayback.Callback {

    @EpoxyAttribute
    PlaybackService mPlayer;

    private PinkMusicView musicView;
    private boolean isBind;

    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_music_model;
    }

    @Override
    public void bind(PinkMusicView view) {
        this.musicView = view;
        musicView.bind(mPlayer);
        this.isBind = true;
    }

    @Override
    public void unbind(PinkMusicView view) {
        super.unbind(view);
        musicView.unBind();
        this.isBind = false;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void onSwitchLast(@Nullable Song last) {
        if (isBind)
            musicView.updateUI(last);
    }

    @Override
    public void onSwitchNext(@Nullable Song next) {
        if (isBind)
            musicView.updateUI(next);
    }

    @Override
    public void onComplete(@Nullable Song next) {
        if (isBind)
            musicView.updateUI(next);
    }

    @Override
    public void onPlayStatusChanged(boolean isPlaying) {
        if (isBind)
            musicView.updatePlayToggle(isPlaying);
    }
}
