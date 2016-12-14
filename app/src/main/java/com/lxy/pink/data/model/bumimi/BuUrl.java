package com.lxy.pink.data.model.bumimi;

/**
 * Created by homelajiang on 2016/12/14 0014.
 */

public class BuUrl {

    /**
     * id : 1
     * verCode : 42
     * verName : 4.2
     * mainurl : http://appplay.bumimi.com:806/video_app.php?url=
     * mainurl_new : http://appplay.bumimi.com:806/video_app_new.php?url=
     * downurl : http://appplay.bumimi.com:806/video_down.php?url=
     * playgf_url : http://playes.bumimi.com:806/playgf.php?url=
     */

    private String id;
    private String verCode;
    private String verName;
    private String mainurl;
    private String mainurl_new;
    private String downurl;
    private String playgf_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public String getMainurl() {
        return mainurl;
    }

    public void setMainurl(String mainurl) {
        this.mainurl = mainurl;
    }

    public String getMainurl_new() {
        return mainurl_new;
    }

    public void setMainurl_new(String mainurl_new) {
        this.mainurl_new = mainurl_new;
    }

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public String getPlaygf_url() {
        return playgf_url;
    }

    public void setPlaygf_url(String playgf_url) {
        this.playgf_url = playgf_url;
    }
}
