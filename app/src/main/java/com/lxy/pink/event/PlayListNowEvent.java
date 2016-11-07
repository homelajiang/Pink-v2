package com.lxy.pink.event;

import com.lxy.pink.data.model.music.PlayList;

public class PlayListNowEvent {

    public PlayList playList;
    public int playIndex;

    public PlayListNowEvent(PlayList playList, int playIndex) {
        this.playList = playList;
        this.playIndex = playIndex;
    }
}