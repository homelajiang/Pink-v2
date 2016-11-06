package com.lxy.pink.data.model.file;

import com.lxy.pink.data.model.music.Song;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */

public class Folder {

    public static final String COLUMN_NAME = "name";
    private int id;

    private String name;
    private String path;
    private String munOfSongs;

    private List<Song> songs = new ArrayList<>();
    private Date createAt;

    public Folder(int id, String name, String path, String munOfSongs,
            Date createAt) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.munOfSongs = munOfSongs;
        this.createAt = createAt;
    }
    public Folder() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getMunOfSongs() {
        return this.munOfSongs;
    }
    public void setMunOfSongs(String munOfSongs) {
        this.munOfSongs = munOfSongs;
    }
    public Date getCreateAt() {
        return this.createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
