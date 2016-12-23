package com.lxy.pink.ui.home.model;

import com.airbnb.epoxy.EpoxyModel;
import com.lxy.pink.R;
import com.lxy.pink.ui.home.view.PinkMusicView;

/**
 * Created by homelajiang on 2016/12/23 0023.
 */

public class PinkMusicModel extends EpoxyModel<PinkMusicView> {
    @Override
    protected int getDefaultLayout() {
        return R.layout.pink_home_music_model;
    }

    @Override
    public void bind(PinkMusicView view) {
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }
}
