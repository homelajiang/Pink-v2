package com.lxy.pink.ui.music;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lxy.pink.R;
import com.lxy.pink.data.model.music.PlayList;
import com.lxy.pink.data.model.music.Song;
import com.lxy.pink.ui.base.adapter.IAdapterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by homelajiang on 2016/11/3 0003.
 */

public class MusicItemHeaderView extends RelativeLayout implements IAdapterView<PlayList> {

    private final Context context;

    public MusicItemHeaderView(Context context) {
        super(context);
        this.context = context;
        View.inflate(context, R.layout.item_play_list_detail_song_header, this);
        ButterKnife.bind(this);
    }

    @Override
    public void bind(PlayList playList, int position) {

    }
}
