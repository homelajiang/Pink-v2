package com.lxy.pink.ui.music.player;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public enum PlayMode {
    SINGLE,
    LOOP,
    LIST,
    SHUFFLE;

    public static PlayMode getDefault() {
        return LIST;
    }

    public static PlayMode switchNextMode(PlayMode currentMode) {
        if (currentMode == null)
            return getDefault();

        switch (currentMode) {
            case LOOP:
                return LIST;
            case LIST:
                return SHUFFLE;
            case SHUFFLE:
                return SINGLE;
            case SINGLE:
                return LOOP;
        }
        return getDefault();
    }
}

