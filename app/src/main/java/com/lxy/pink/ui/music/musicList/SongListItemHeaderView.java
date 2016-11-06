package com.lxy.pink.ui.music.musicList;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.ui.base.adapter.IAdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public class SongListItemHeaderView extends RelativeLayout implements IAdapterView<PlayList> {

    public SongListItemHeaderView(Context context) {
        super(context);
        View.inflate(context, R.layout.item_play_list_detail_song_header, this);
        ButterKnife.bind(this);
    }

    @Override
    public void bind(PlayList playList, int position) {

    }
}
