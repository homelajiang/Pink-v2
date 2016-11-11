package com.lxy.pink.ui.music.musicList;

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

public class SongListItemHeaderView extends RelativeLayout implements IAdapterView<PlayList> {

    private final Context context;

    public SongListItemHeaderView(Context context) {
        super(context);
        this.context = context;
        View.inflate(context, R.layout.item_play_list_detail_song_header, this);
        ButterKnife.bind(this);
    }

    @OnCheckedChanged({R.id.radio_btn_item, R.id.radio_btn_list})
    public void onRadioButtonChecked(RadioButton button, boolean isChecked) {
        if (isChecked) {
            Toast.makeText(context,"fjdskf",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void bind(PlayList playList, int position) {

    }
}
