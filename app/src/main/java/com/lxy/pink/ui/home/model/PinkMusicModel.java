package com.lxy.pink.ui.home.model;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.player.PlaybackService;
import com.lxy.pink.ui.home.view.PinkMusicView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkMusicModel extends EpoxyModel<PinkMusicView> {

    @EpoxyAttribute
    PlaybackService mPlayer;

    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_music_model;
    }

    @Override
    public void bind(PinkMusicView view) {
        view.bind(mPlayer);
    }

    @Override
    public void unbind(PinkMusicView view) {
        view.unBind();
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
