package com.lxy.pink.ui.home.model;

import android.support.annotation.Nullable;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.player.IPlayback;
import com.lxy.pink.ui.home.view.PinkMusicView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkMusicModel extends EpoxyModel<PinkMusicView> implements IPlayback.Callback{

    @EpoxyAttribute
    Song song;

    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_music_model;
    }

    @Override
    public void bind(PinkMusicView view) {

    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    @Override
    public void onSwitchLast(@Nullable Song last) {

    }

    @Override
    public void onSwitchNext(@Nullable Song next) {

    }

    @Override
    public void onComplete(@Nullable Song next) {

    }

    @Override
    public void onPlayStatusChanged(boolean isPlaying) {

    }
}
