package com.lxy.pink.data.model.acfun;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoComment {

    /**
     * id : 73366098
     * quoteId : 73362259
     * refCount : 1
     * content : 你要多考虑我们股东的意见 起码每个视频两条防不胜防 我可不想等到举不起来时在去E3
     * time : 1488256076000
     * userId : 375981
     * username : 谁好人卡有我多
     * avatar : http://cdn.aixifan.com/dotnet/20120923/style/image/avatar.jpg
     * floor : 220
     * deep : 3
     * isAt : 0
     * nameRed : 1
     * avatarFrame : 1
     * isDelete : false
     * isUpDelete : false
     */

    private int id;
    private int quoteId;
    private int refCount;
    private String content;
    private long time;
    private int userId;
    private String username;
    private String avatar;
    private int floor;
    private int deep;
    private int isAt;
    private int nameRed;
    private int avatarFrame;
    private boolean isDelete;
    private boolean isUpDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public int getIsAt() {
        return isAt;
    }

    public void setIsAt(int isAt) {
        this.isAt = isAt;
    }

    public int getNameRed() {
        return nameRed;
    }

    public void setNameRed(int nameRed) {
        this.nameRed = nameRed;
    }

    public int getAvatarFrame() {
        return avatarFrame;
    }

    public void setAvatarFrame(int avatarFrame) {
        this.avatarFrame = avatarFrame;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isIsUpDelete() {
        return isUpDelete;
    }

    public void setIsUpDelete(boolean isUpDelete) {
        this.isUpDelete = isUpDelete;
    }
}
