package com.lxy.pink.data.model.acfun;

import java.util.List;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACRecommend {

    /**
     * code : 200
     * data : [{"belong":0,"channelId":0,"children":[],"contentCount":3,"contents":[{"channelId":86,"image":"http://imgs.aixifan.com/content/2016_11_18/1482079661.png","intro":"大宋提刑官 第五回\r<br/>微服出巡宋提刑验骨\r<br/>袖里乾坤刁知县渡河\r<br/>\r<br/>\r<br/>\r<br/>\r<br/>反正没有人看简介，这次我也不说什么了。","owner":{"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201604/271436023tt5vr1z.jpg","id":4295944,"name":"怪异故事"},"regionId":183,"releaseDate":1482079972000,"status":2,"title":"26【怪异君毁经典】《大宋提刑官》 第五回","url":"3339182","visit":{"comments":474,"danmakuSize":1868,"goldBanana":14442,"score":0,"stows":653,"ups":0,"views":349485}},{"channelId":86,"image":"http://imgs.aixifan.com/content/2016_11_19/1482112351.jpg","intro":"春运将至，一票难求，四基佬一言不合就真的不合了......","owner":{"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201305/09111617of3w.jpg","id":339880,"name":"大蝈小酱"},"regionId":183,"releaseDate":1482112870000,"status":2,"title":"《名著村抢票风云》","url":"3339625","visit":{"comments":113,"danmakuSize":418,"goldBanana":7459,"score":0,"stows":389,"ups":0,"views":79872}},{"channelId":86,"image":"http://imgs.aixifan.com/content/2016_11_19/1482112812.png","intro":"|视频素材来源于网络","owner":{"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201612/09150006fra8exyu.jpg","id":8696038,"name":"视频看遍世界"},"regionId":183,"releaseDate":1482113048000,"status":2,"title":"老外竟让某些外国人滚出中国，说得太好了！","url":"3339635","visit":{"comments":336,"danmakuSize":245,"goldBanana":5614,"score":0,"stows":450,"ups":0,"views":158433}}],"deviceType":-1,"goText":"","hide":2,"id":183,"image":"http://imgs.aixifan.com/cms/2016_09_21/1474442860304.png","menuCount":0,"name":"香蕉排行榜","pid":-1,"platformId":1,"showLine":1,"showMore":0,"showName":1,"sort":101,"subTitle":"1","type":{"id":12,"name":"视频_香蕉榜","value":"videos_banana_list"},"updateAt":1474442858000,"url":"","version":"4.1.0","versionSign":2}]
     * message : OK
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * belong : 0
         * channelId : 0
         * children : []
         * contentCount : 3
         * contents : [{"channelId":86,"image":"http://imgs.aixifan.com/content/2016_11_18/1482079661.png","intro":"大宋提刑官 第五回\r<br/>微服出巡宋提刑验骨\r<br/>袖里乾坤刁知县渡河\r<br/>\r<br/>\r<br/>\r<br/>\r<br/>反正没有人看简介，这次我也不说什么了。","owner":{"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201604/271436023tt5vr1z.jpg","id":4295944,"name":"怪异故事"},"regionId":183,"releaseDate":1482079972000,"status":2,"title":"26【怪异君毁经典】《大宋提刑官》 第五回","url":"3339182","visit":{"comments":474,"danmakuSize":1868,"goldBanana":14442,"score":0,"stows":653,"ups":0,"views":349485}},{"channelId":86,"image":"http://imgs.aixifan.com/content/2016_11_19/1482112351.jpg","intro":"春运将至，一票难求，四基佬一言不合就真的不合了......","owner":{"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201305/09111617of3w.jpg","id":339880,"name":"大蝈小酱"},"regionId":183,"releaseDate":1482112870000,"status":2,"title":"《名著村抢票风云》","url":"3339625","visit":{"comments":113,"danmakuSize":418,"goldBanana":7459,"score":0,"stows":389,"ups":0,"views":79872}},{"channelId":86,"image":"http://imgs.aixifan.com/content/2016_11_19/1482112812.png","intro":"|视频素材来源于网络","owner":{"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201612/09150006fra8exyu.jpg","id":8696038,"name":"视频看遍世界"},"regionId":183,"releaseDate":1482113048000,"status":2,"title":"老外竟让某些外国人滚出中国，说得太好了！","url":"3339635","visit":{"comments":336,"danmakuSize":245,"goldBanana":5614,"score":0,"stows":450,"ups":0,"views":158433}}]
         * deviceType : -1
         * goText :
         * hide : 2
         * id : 183
         * image : http://imgs.aixifan.com/cms/2016_09_21/1474442860304.png
         * menuCount : 0
         * name : 香蕉排行榜
         * pid : -1
         * platformId : 1
         * showLine : 1
         * showMore : 0
         * showName : 1
         * sort : 101
         * subTitle : 1
         * type : {"id":12,"name":"视频_香蕉榜","value":"videos_banana_list"}
         * updateAt : 1474442858000
         * url :
         * version : 4.1.0
         * versionSign : 2
         */

        private int belong;
        private int channelId;
        private int contentCount;
        private int deviceType;
        private String goText;
        private int hide;
        private int id;
        private String image;
        private int menuCount;
        private String name;
        private int pid;
        private int platformId;
        private int showLine;
        private int showMore;
        private int showName;
        private int sort;
        private String subTitle;
        private TypeBean type;
        private long updateAt;
        private String url;
        private String version;
        private int versionSign;
        private List<?> children;
        private List<ContentsBean> contents;

        public int getBelong() {
            return belong;
        }

        public void setBelong(int belong) {
            this.belong = belong;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public int getContentCount() {
            return contentCount;
        }

        public void setContentCount(int contentCount) {
            this.contentCount = contentCount;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getGoText() {
            return goText;
        }

        public void setGoText(String goText) {
            this.goText = goText;
        }

        public int getHide() {
            return hide;
        }

        public void setHide(int hide) {
            this.hide = hide;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getMenuCount() {
            return menuCount;
        }

        public void setMenuCount(int menuCount) {
            this.menuCount = menuCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPlatformId() {
            return platformId;
        }

        public void setPlatformId(int platformId) {
            this.platformId = platformId;
        }

        public int getShowLine() {
            return showLine;
        }

        public void setShowLine(int showLine) {
            this.showLine = showLine;
        }

        public int getShowMore() {
            return showMore;
        }

        public void setShowMore(int showMore) {
            this.showMore = showMore;
        }

        public int getShowName() {
            return showName;
        }

        public void setShowName(int showName) {
            this.showName = showName;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public TypeBean getType() {
            return type;
        }

        public void setType(TypeBean type) {
            this.type = type;
        }

        public long getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(long updateAt) {
            this.updateAt = updateAt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVersionSign() {
            return versionSign;
        }

        public void setVersionSign(int versionSign) {
            this.versionSign = versionSign;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }

        public List<ContentsBean> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBean> contents) {
            this.contents = contents;
        }

        public static class TypeBean {
            /**
             * id : 12
             * name : 视频_香蕉榜
             * value : videos_banana_list
             */

            private int id;
            private String name;
            private String value;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class ContentsBean {
            /**
             * channelId : 86
             * image : http://imgs.aixifan.com/content/2016_11_18/1482079661.png
             * intro : 大宋提刑官 第五回<br/>微服出巡宋提刑验骨<br/>袖里乾坤刁知县渡河<br/><br/><br/><br/><br/>反正没有人看简介，这次我也不说什么了。
             * owner : {"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201604/271436023tt5vr1z.jpg","id":4295944,"name":"怪异故事"}
             * regionId : 183
             * releaseDate : 1482079972000
             * status : 2
             * title : 26【怪异君毁经典】《大宋提刑官》 第五回
             * url : 3339182
             * visit : {"comments":474,"danmakuSize":1868,"goldBanana":14442,"score":0,"stows":653,"ups":0,"views":349485}
             */

            private int channelId;
            private String image;
            private String intro;
            private OwnerBean owner;
            private int regionId;
            private long releaseDate;
            private int status;
            private String title;
            private String url;
            private VisitBean visit;

            public int getChannelId() {
                return channelId;
            }

            public void setChannelId(int channelId) {
                this.channelId = channelId;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public OwnerBean getOwner() {
                return owner;
            }

            public void setOwner(OwnerBean owner) {
                this.owner = owner;
            }

            public int getRegionId() {
                return regionId;
            }

            public void setRegionId(int regionId) {
                this.regionId = regionId;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public VisitBean getVisit() {
                return visit;
            }

            public void setVisit(VisitBean visit) {
                this.visit = visit;
            }

            public static class OwnerBean {
                /**
                 * avatar : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201604/271436023tt5vr1z.jpg
                 * id : 4295944
                 * name : 怪异故事
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
                 * comments : 474
                 * danmakuSize : 1868
                 * goldBanana : 14442
                 * score : 0
                 * stows : 653
                 * ups : 0
                 * views : 349485
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
        }
    }
}
