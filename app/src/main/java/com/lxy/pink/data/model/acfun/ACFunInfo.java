package com.lxy.pink.data.model.acfun;

import java.util.List;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACFunInfo {

    /**
     * code : 200
     * data : {"allowDanmaku":0,"bangumiId":1480028,"copyright":0,"cover":"http://imgs.aixifan.com/cms/2016_10_04/1475514099318.jpg","displayIos":1,"displayMobile":3,"displayWeb":3,"intro":"高中开学典礼当天与制作同人游戏的社团\u201cSNS部\u201d相遇的本田珠辉。她觉得自己找到了想要做的事情而入部，但不论是绘图、编程、剧本还是音乐，全部都要从零开始......","isComment":true,"latestVideo":{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4615175,"sort":110,"sourceId":"4592273","sourceType":"zhuzhan","startTime":0,"title":"第11话","updateTime":1481511616000,"videoId":4615175,"visibleLevel":-1},"month":10,"playWay":2,"status":1,"tags":[{"cover":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201408/07105852n2p6.jpg","id":237,"name":"搞笑","oldTagId":237,"sort":26,"type":1},{"cover":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201410/11100304lbb2.jpg","id":1268,"name":"日本","oldTagId":1268,"sort":4,"type":1},{"cover":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201510/09163555vl3rzj01.jpg","id":2000093,"name":"校园","sort":36,"type":1},{"cover":"","id":2000100,"name":"美少女","sort":43,"type":1}],"title":"斯特拉的魔法","type":1,"videoCount":11,"videos":[{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4615175,"sort":110,"sourceId":"4592273","sourceType":"zhuzhan","startTime":0,"title":"第11话","updateTime":1481511616000,"videoId":4615175,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4584906,"sort":100,"sourceId":"4563013","sourceType":"zhuzhan","startTime":0,"title":"第10话","videoId":4584906,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4553079,"sort":90,"sourceId":"4533257","sourceType":"zhuzhan","startTime":0,"title":"第9话","videoId":4553079,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4516073,"sort":80,"sourceId":"4499060","sourceType":"zhuzhan","startTime":0,"title":"第8话","videoId":4516073,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4483163,"sort":70,"sourceId":"4468723","sourceType":"zhuzhan","startTime":0,"title":"第7话","videoId":4483163,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4448024,"sort":60,"sourceId":"4435526","sourceType":"zhuzhan","startTime":0,"title":"第6话","videoId":4448024,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4408169,"sort":50,"sourceId":"4395890","sourceType":"zhuzhan","startTime":0,"title":"第5话","videoId":4408169,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4377266,"sort":40,"sourceId":"4366936","sourceType":"zhuzhan","startTime":0,"title":"第4话","videoId":4377266,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4345222,"sort":30,"sourceId":"4337093","sourceType":"zhuzhan","startTime":0,"title":"第3话","videoId":4345222,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4309097,"sort":20,"sourceId":"4303408","sourceType":"zhuzhan","startTime":0,"title":"第2话","videoId":4309097,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4278568,"sort":10,"sourceId":"4273350","sourceType":"zhuzhan","startTime":0,"title":"第1话","videoId":4278568,"visibleLevel":-1}],"visibleLevel":-1,"visit":{"comments":93,"goldBanana":0,"goldBananaVoters":0,"score":0,"stows":2324,"ups":0,"views":196242},"week":1,"year":2016}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * allowDanmaku : 0
         * bangumiId : 1480028
         * copyright : 0
         * cover : http://imgs.aixifan.com/cms/2016_10_04/1475514099318.jpg
         * displayIos : 1
         * displayMobile : 3
         * displayWeb : 3
         * intro : 高中开学典礼当天与制作同人游戏的社团“SNS部”相遇的本田珠辉。她觉得自己找到了想要做的事情而入部，但不论是绘图、编程、剧本还是音乐，全部都要从零开始......
         * isComment : true
         * latestVideo : {"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4615175,"sort":110,"sourceId":"4592273","sourceType":"zhuzhan","startTime":0,"title":"第11话","updateTime":1481511616000,"videoId":4615175,"visibleLevel":-1}
         * month : 10
         * playWay : 2
         * status : 1
         * tags : [{"cover":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201408/07105852n2p6.jpg","id":237,"name":"搞笑","oldTagId":237,"sort":26,"type":1},{"cover":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201410/11100304lbb2.jpg","id":1268,"name":"日本","oldTagId":1268,"sort":4,"type":1},{"cover":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201510/09163555vl3rzj01.jpg","id":2000093,"name":"校园","sort":36,"type":1},{"cover":"","id":2000100,"name":"美少女","sort":43,"type":1}]
         * title : 斯特拉的魔法
         * type : 1
         * videoCount : 11
         * videos : [{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4615175,"sort":110,"sourceId":"4592273","sourceType":"zhuzhan","startTime":0,"title":"第11话","updateTime":1481511616000,"videoId":4615175,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4584906,"sort":100,"sourceId":"4563013","sourceType":"zhuzhan","startTime":0,"title":"第10话","videoId":4584906,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4553079,"sort":90,"sourceId":"4533257","sourceType":"zhuzhan","startTime":0,"title":"第9话","videoId":4553079,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4516073,"sort":80,"sourceId":"4499060","sourceType":"zhuzhan","startTime":0,"title":"第8话","videoId":4516073,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4483163,"sort":70,"sourceId":"4468723","sourceType":"zhuzhan","startTime":0,"title":"第7话","videoId":4483163,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4448024,"sort":60,"sourceId":"4435526","sourceType":"zhuzhan","startTime":0,"title":"第6话","videoId":4448024,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4408169,"sort":50,"sourceId":"4395890","sourceType":"zhuzhan","startTime":0,"title":"第5话","videoId":4408169,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4377266,"sort":40,"sourceId":"4366936","sourceType":"zhuzhan","startTime":0,"title":"第4话","videoId":4377266,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4345222,"sort":30,"sourceId":"4337093","sourceType":"zhuzhan","startTime":0,"title":"第3话","videoId":4345222,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4309097,"sort":20,"sourceId":"4303408","sourceType":"zhuzhan","startTime":0,"title":"第2话","videoId":4309097,"visibleLevel":-1},{"allowDanmaku":0,"bangumiId":1480028,"danmakuId":4278568,"sort":10,"sourceId":"4273350","sourceType":"zhuzhan","startTime":0,"title":"第1话","videoId":4278568,"visibleLevel":-1}]
         * visibleLevel : -1
         * visit : {"comments":93,"goldBanana":0,"goldBananaVoters":0,"score":0,"stows":2324,"ups":0,"views":196242}
         * week : 1
         * year : 2016
         */

        private int allowDanmaku;
        private int bangumiId;
        private int copyright;
        private String cover;
        private int displayIos;
        private int displayMobile;
        private int displayWeb;
        private String intro;
        private boolean isComment;
        private LatestVideoBean latestVideo;
        private int month;
        private int playWay;
        private int status;
        private String title;
        private int type;
        private int videoCount;
        private int visibleLevel;
        private VisitBean visit;
        private int week;
        private int year;
        private List<TagsBean> tags;
        private List<VideosBean> videos;

        public int getAllowDanmaku() {
            return allowDanmaku;
        }

        public void setAllowDanmaku(int allowDanmaku) {
            this.allowDanmaku = allowDanmaku;
        }

        public int getBangumiId() {
            return bangumiId;
        }

        public void setBangumiId(int bangumiId) {
            this.bangumiId = bangumiId;
        }

        public int getCopyright() {
            return copyright;
        }

        public void setCopyright(int copyright) {
            this.copyright = copyright;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getDisplayIos() {
            return displayIos;
        }

        public void setDisplayIos(int displayIos) {
            this.displayIos = displayIos;
        }

        public int getDisplayMobile() {
            return displayMobile;
        }

        public void setDisplayMobile(int displayMobile) {
            this.displayMobile = displayMobile;
        }

        public int getDisplayWeb() {
            return displayWeb;
        }

        public void setDisplayWeb(int displayWeb) {
            this.displayWeb = displayWeb;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public boolean isIsComment() {
            return isComment;
        }

        public void setIsComment(boolean isComment) {
            this.isComment = isComment;
        }

        public LatestVideoBean getLatestVideo() {
            return latestVideo;
        }

        public void setLatestVideo(LatestVideoBean latestVideo) {
            this.latestVideo = latestVideo;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getPlayWay() {
            return playWay;
        }

        public void setPlayWay(int playWay) {
            this.playWay = playWay;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public int getVisibleLevel() {
            return visibleLevel;
        }

        public void setVisibleLevel(int visibleLevel) {
            this.visibleLevel = visibleLevel;
        }

        public VisitBean getVisit() {
            return visit;
        }

        public void setVisit(VisitBean visit) {
            this.visit = visit;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class LatestVideoBean {
            /**
             * allowDanmaku : 0
             * bangumiId : 1480028
             * danmakuId : 4615175
             * sort : 110
             * sourceId : 4592273
             * sourceType : zhuzhan
             * startTime : 0
             * title : 第11话
             * updateTime : 1481511616000
             * videoId : 4615175
             * visibleLevel : -1
             */

            private int allowDanmaku;
            private int bangumiId;
            private int danmakuId;
            private int sort;
            private String sourceId;
            private String sourceType;
            private int startTime;
            private String title;
            private long updateTime;
            private int videoId;
            private int visibleLevel;

            public int getAllowDanmaku() {
                return allowDanmaku;
            }

            public void setAllowDanmaku(int allowDanmaku) {
                this.allowDanmaku = allowDanmaku;
            }

            public int getBangumiId() {
                return bangumiId;
            }

            public void setBangumiId(int bangumiId) {
                this.bangumiId = bangumiId;
            }

            public int getDanmakuId() {
                return danmakuId;
            }

            public void setDanmakuId(int danmakuId) {
                this.danmakuId = danmakuId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
            }

            public String getSourceType() {
                return sourceType;
            }

            public void setSourceType(String sourceType) {
                this.sourceType = sourceType;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public int getVisibleLevel() {
                return visibleLevel;
            }

            public void setVisibleLevel(int visibleLevel) {
                this.visibleLevel = visibleLevel;
            }
        }

        public static class VisitBean {
            /**
             * comments : 93
             * goldBanana : 0
             * goldBananaVoters : 0
             * score : 0
             * stows : 2324
             * ups : 0
             * views : 196242
             */

            private int comments;
            private int goldBanana;
            private int goldBananaVoters;
            private int score;
            private int stows;
            private int ups;
            private int views;

            public int getComments() {
                return comments;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }

            public int getGoldBanana() {
                return goldBanana;
            }

            public void setGoldBanana(int goldBanana) {
                this.goldBanana = goldBanana;
            }

            public int getGoldBananaVoters() {
                return goldBananaVoters;
            }

            public void setGoldBananaVoters(int goldBananaVoters) {
                this.goldBananaVoters = goldBananaVoters;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getStows() {
                return stows;
            }

            public void setStows(int stows) {
                this.stows = stows;
            }

            public int getUps() {
                return ups;
            }

            public void setUps(int ups) {
                this.ups = ups;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }
        }

        public static class TagsBean {
            /**
             * cover : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201408/07105852n2p6.jpg
             * id : 237
             * name : 搞笑
             * oldTagId : 237
             * sort : 26
             * type : 1
             */

            private String cover;
            private int id;
            private String name;
            private int oldTagId;
            private int sort;
            private int type;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOldTagId() {
                return oldTagId;
            }

            public void setOldTagId(int oldTagId) {
                this.oldTagId = oldTagId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class VideosBean {
            /**
             * allowDanmaku : 0
             * bangumiId : 1480028
             * danmakuId : 4615175
             * sort : 110
             * sourceId : 4592273
             * sourceType : zhuzhan
             * startTime : 0
             * title : 第11话
             * updateTime : 1481511616000
             * videoId : 4615175
             * visibleLevel : -1
             */

            private int allowDanmaku;
            private int bangumiId;
            private int danmakuId;
            private int sort;
            private String sourceId;
            private String sourceType;
            private int startTime;
            private String title;
            private long updateTime;
            private int videoId;
            private int visibleLevel;

            public int getAllowDanmaku() {
                return allowDanmaku;
            }

            public void setAllowDanmaku(int allowDanmaku) {
                this.allowDanmaku = allowDanmaku;
            }

            public int getBangumiId() {
                return bangumiId;
            }

            public void setBangumiId(int bangumiId) {
                this.bangumiId = bangumiId;
            }

            public int getDanmakuId() {
                return danmakuId;
            }

            public void setDanmakuId(int danmakuId) {
                this.danmakuId = danmakuId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
            }

            public String getSourceType() {
                return sourceType;
            }

            public void setSourceType(String sourceType) {
                this.sourceType = sourceType;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public int getVisibleLevel() {
                return visibleLevel;
            }

            public void setVisibleLevel(int visibleLevel) {
                this.visibleLevel = visibleLevel;
            }
        }
    }
}
