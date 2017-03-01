package com.lxy.pink.data.model.acfun;

import java.util.List;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACVideoInfo {

    /**
     * code : 200
     * data : {"channelId":86,"contentId":3326059,"cover":"http://imgs.aixifan.com/content/2016_11_13/1481612272.jpg","description":"【完爆你的视觉】那些炫酷的壁纸（第一期）\r<br/>       <br/> \r<br/>链接：http://pan.baidu.com/s/1nvM0v0l\r<br/>密码：9b67\r<br/>\r<br/>【完爆你的视觉】美如画1080P壁纸（第二期）\r<br/> <br/>         \r<br/>链接：http://pan.baidu.com/s/1i5hryi5\r<br/>密码：pqe6\r<br/>\r<br/>【完爆你的视觉】卡哇伊1080P壁纸（第三期）","display":0,"isArticle":0,"isComment":true,"isRecommend":0,"owner":{"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201611/21115744boikgj9a.jpg","id":1175322,"name":"一头叱咤风云的狗"},"parentChannelId":60,"releaseDate":1481610263000,"status":2,"title":"【完爆你的视觉】福利不多1080P壁纸（第四期）","topLevel":0,"updatedAt":1482159087000,"videoCount":1,"videos":[{"allowDanmaku":0,"commentId":4616149,"danmakuId":4616149,"sort":0,"sourceId":"4593227","sourceType":"zhuzhan","startTime":0,"time":281,"title":"Part1","url":"","videoId":4616149,"visibleLevel":-1}],"viewOnly":0,"visit":{"comments":1147,"danmakuSize":161,"goldBanana":5581,"score":0,"stows":1106,"ups":0,"views":126719}}
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
         * channelId : 86
         * contentId : 3326059
         * cover : http://imgs.aixifan.com/content/2016_11_13/1481612272.jpg
         * description : 【完爆你的视觉】那些炫酷的壁纸（第一期）<br/>       <br/> <br/>链接：http://pan.baidu.com/s/1nvM0v0l<br/>密码：9b67<br/><br/>【完爆你的视觉】美如画1080P壁纸（第二期）<br/> <br/>         <br/>链接：http://pan.baidu.com/s/1i5hryi5<br/>密码：pqe6<br/><br/>【完爆你的视觉】卡哇伊1080P壁纸（第三期）
         * display : 0
         * isArticle : 0
         * isComment : true
         * isRecommend : 0
         * owner : {"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201611/21115744boikgj9a.jpg","id":1175322,"name":"一头叱咤风云的狗"}
         * parentChannelId : 60
         * releaseDate : 1481610263000
         * status : 2
         * title : 【完爆你的视觉】福利不多1080P壁纸（第四期）
         * topLevel : 0
         * updatedAt : 1482159087000
         * videoCount : 1
         * videos : [{"allowDanmaku":0,"commentId":4616149,"danmakuId":4616149,"sort":0,"sourceId":"4593227","sourceType":"zhuzhan","startTime":0,"time":281,"title":"Part1","url":"","videoId":4616149,"visibleLevel":-1}]
         * viewOnly : 0
         * visit : {"comments":1147,"danmakuSize":161,"goldBanana":5581,"score":0,"stows":1106,"ups":0,"views":126719}
         */

        private int channelId;
        private int contentId;
        private String cover;
        private String description;
        private int display;
        private int isArticle;
        private boolean isComment;
        private int isRecommend;
        private OwnerBean owner;
        private int parentChannelId;
        private long releaseDate;
        private int status;
        private String title;
        private int topLevel;
        private long updatedAt;
        private int videoCount;
        private int viewOnly;
        private VisitBean visit;
        private List<VideosBean> videos;

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public int getContentId() {
            return contentId;
        }

        public void setContentId(int contentId) {
            this.contentId = contentId;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public int getIsArticle() {
            return isArticle;
        }

        public void setIsArticle(int isArticle) {
            this.isArticle = isArticle;
        }

        public boolean isIsComment() {
            return isComment;
        }

        public void setIsComment(boolean isComment) {
            this.isComment = isComment;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public OwnerBean getOwner() {
            return owner;
        }

        public void setOwner(OwnerBean owner) {
            this.owner = owner;
        }

        public int getParentChannelId() {
            return parentChannelId;
        }

        public void setParentChannelId(int parentChannelId) {
            this.parentChannelId = parentChannelId;
        }

        public long getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(long releaseDate) {
            this.releaseDate = releaseDate;
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

        public int getTopLevel() {
            return topLevel;
        }

        public void setTopLevel(int topLevel) {
            this.topLevel = topLevel;
        }

        public long getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public int getViewOnly() {
            return viewOnly;
        }

        public void setViewOnly(int viewOnly) {
            this.viewOnly = viewOnly;
        }

        public VisitBean getVisit() {
            return visit;
        }

        public void setVisit(VisitBean visit) {
            this.visit = visit;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class OwnerBean {
            /**
             * avatar : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201611/21115744boikgj9a.jpg
             * id : 1175322
             * name : 一头叱咤风云的狗
             */

            private String avatar;
            private int id;
            private String name;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
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
        }

        public static class VisitBean {
            /**
             * comments : 1147
             * danmakuSize : 161
             * goldBanana : 5581
             * score : 0
             * stows : 1106
             * ups : 0
             * views : 126719
             */

            private int comments;
            private int danmakuSize;
            private int goldBanana;
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

            public int getDanmakuSize() {
                return danmakuSize;
            }

            public void setDanmakuSize(int danmakuSize) {
                this.danmakuSize = danmakuSize;
            }

            public int getGoldBanana() {
                return goldBanana;
            }

            public void setGoldBanana(int goldBanana) {
                this.goldBanana = goldBanana;
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

        public static class VideosBean {
            /**
             * allowDanmaku : 0
             * commentId : 4616149
             * danmakuId : 4616149
             * sort : 0
             * sourceId : 4593227
             * sourceType : zhuzhan
             * startTime : 0
             * time : 281
             * title : Part1
             * url :
             * videoId : 4616149
             * visibleLevel : -1
             */

            private int allowDanmaku;
            private int commentId;
            private int danmakuId;
            private int sort;
            private String sourceId;
            private String sourceType;
            private int startTime;
            private int time;
            private String title;
            private String url;
            private int videoId;
            private int visibleLevel;

            public int getAllowDanmaku() {
                return allowDanmaku;
            }

            public void setAllowDanmaku(int allowDanmaku) {
                this.allowDanmaku = allowDanmaku;
            }

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
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

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
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
