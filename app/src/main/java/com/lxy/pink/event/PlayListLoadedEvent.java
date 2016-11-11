package com.lxy.pink.event;

import com.lxy.pink.data.model.music.PlayList;

/**
 * Created by homelajiang on 2016/11/11 0011.
 */

public class PlayListLoadedEvent {
    public PlayList playList;

    public PlayListLoadedEvent(PlayList playList) {
        this.playList = playList;
    }
}
