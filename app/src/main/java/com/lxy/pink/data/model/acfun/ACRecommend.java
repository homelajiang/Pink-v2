package com.lxy.pink.data.model.acfun;

import java.util.List;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACRecommend {

    /**
     * code : 200
     * data : [{"belong":0,"channelId":0,"children":[],"contentCount":3,"contents":[{"actionId":2,"channelId":86,"device":0,"hide":0,"id":53418,"image":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201612/21105106k48hivfm.jpg","intro":"BGM和封面度娘下载地址：http://pan.baidu.com/s/1c2FYJFa<br/>BGM网易云音乐：http://music.163.com/#/m/playlist?id=525915031 毁歌：[ac3335566] <br/>上一期回顾：[ac3302268]　催泪篇：[ac3229080] 总合辑：[aa929]  提前祝大家圣诞节快乐！交换圣诞礼物点我[ac3344116]","latestBangumiVideo":{"bangumiId":1480038,"sort":100,"title":"第10话","updateTime":1482140235000,"videoId":4643853},"owner":{"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201611/16133520hzgcnklc.jpg","id":243400,"name":"鲁路修·VI布里塔尼亚"},"regionId":183,"releasedAt":1482119983000,"releaseDate":1482299389000,"shareTagShow":0,"status":2,"subTitle":"","subUrl":"","title":"AcFun评论才是本体【2016年12月篇】上","updateAt":1482119984000,"url":"3345231","visibleLevel":-1,"visit":{"comments":484,"danmakuSize":2628,"goldBanana":9559,"goldBananaVoters":0,"score":0,"stows":514,"ups":0,"views":154588},"webImage":"","webUrl":""}],"deviceType":-1,"goText":"","hide":2,"id":183,"image":"http://imgs.aixifan.com/cms/2016_09_21/1474442860304.png","menuCount":0,"menus":[{"actionId":6,"channelId":155,"hide":0,"id":4,"image":"","name":"更多番剧内容","regionId":7,"sort":0,"url":""}],"name":"香蕉排行榜","pid":-1,"platformId":1,"showLine":1,"showMore":0,"showName":1,"sort":101,"subTitle":"1","type":{"id":12,"name":"视频_香蕉榜","value":"videos_banana_list"},"updateAt":1474442858000,"url":"","version":"4.1.0","versionSign":2}]
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
         * contents : [{"actionId":2,"channelId":86,"device":0,"hide":0,"id":53418,"image":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201612/21105106k48hivfm.jpg","intro":"BGM和封面度娘下载地址：http://pan.baidu.com/s/1c2FYJFa<br/>BGM网易云音乐：http://music.163.com/#/m/playlist?id=525915031 毁歌：[ac3335566] <br/>上一期回顾：[ac3302268]　催泪篇：[ac3229080] 总合辑：[aa929]  提前祝大家圣诞节快乐！交换圣诞礼物点我[ac3344116]","latestBangumiVideo":{"bangumiId":1480038,"sort":100,"title":"第10话","updateTime":1482140235000,"videoId":4643853},"owner":{"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201611/16133520hzgcnklc.jpg","id":243400,"name":"鲁路修·VI布里塔尼亚"},"regionId":183,"releasedAt":1482119983000,"releaseDate":1482299389000,"shareTagShow":0,"status":2,"subTitle":"","subUrl":"","title":"AcFun评论才是本体【2016年12月篇】上","updateAt":1482119984000,"url":"3345231","visibleLevel":-1,"visit":{"comments":484,"danmakuSize":2628,"goldBanana":9559,"goldBananaVoters":0,"score":0,"stows":514,"ups":0,"views":154588},"webImage":"","webUrl":""}]
         * deviceType : -1
         * goText :
         * hide : 2
         * id : 183
         * image : http://imgs.aixifan.com/cms/2016_09_21/1474442860304.png
         * menuCount : 0
         * menus : [{"actionId":6,"channelId":155,"hide":0,"id":4,"image":"","name":"更多番剧内容","regionId":7,"sort":0,"url":""}]
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
        private List<MenusBean> menus;

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

        public List<MenusBean> getMenus() {
            return menus;
        }

        public void setMenus(List<MenusBean> menus) {
            this.menus = menus;
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
             * actionId : 2
             * channelId : 86
             * device : 0
             * hide : 0
             * id : 53418
             * image : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201612/21105106k48hivfm.jpg
             * intro : BGM和封面度娘下载地址：http://pan.baidu.com/s/1c2FYJFa<br/>BGM网易云音乐：http://music.163.com/#/m/playlist?id=525915031 毁歌：[ac3335566] <br/>上一期回顾：[ac3302268]　催泪篇：[ac3229080] 总合辑：[aa929]  提前祝大家圣诞节快乐！交换圣诞礼物点我[ac3344116]
             * latestBangumiVideo : {"bangumiId":1480038,"sort":100,"title":"第10话","updateTime":1482140235000,"videoId":4643853}
             * owner : {"avatar":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201611/16133520hzgcnklc.jpg","id":243400,"name":"鲁路修·VI布里塔尼亚"}
             * regionId : 183
             * releasedAt : 1482119983000
             * releaseDate : 1482299389000
             * shareTagShow : 0
             * status : 2
             * subTitle :
             * subUrl :
             * title : AcFun评论才是本体【2016年12月篇】上
             * updateAt : 1482119984000
             * url : 3345231
             * visibleLevel : -1
             * visit : {"comments":484,"danmakuSize":2628,"goldBanana":9559,"goldBananaVoters":0,"score":0,"stows":514,"ups":0,"views":154588}
             * webImage :
             * webUrl :
             */

            private int actionId;
            private int channelId;
            private int device;
            private int hide;
            private int id;
            private String image;
            private String intro;
            private LatestBangumiVideoBean latestBangumiVideo;
            private OwnerBean owner;
            private int regionId;
            private long releasedAt;
            private long releaseDate;
            private int shareTagShow;
            private int status;
            private String subTitle;
            private String subUrl;
            private String title;
            private long updateAt;
            private String url;
            private int visibleLevel;
            private VisitBean visit;
            private String webImage;
            private String webUrl;

            public int getActionId() {
                return actionId;
            }

            public void setActionId(int actionId) {
                this.actionId = actionId;
            }

            public int getChannelId() {
                return channelId;
            }

            public void setChannelId(int channelId) {
                this.channelId = channelId;
            }

            public int getDevice() {
                return device;
            }

            public void setDevice(int device) {
                this.device = device;
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

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public LatestBangumiVideoBean getLatestBangumiVideo() {
                return latestBangumiVideo;
            }

            public void setLatestBangumiVideo(LatestBangumiVideoBean latestBangumiVideo) {
                this.latestBangumiVideo = latestBangumiVideo;
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

            public long getReleasedAt() {
                return releasedAt;
            }

            public void setReleasedAt(long releasedAt) {
                this.releasedAt = releasedAt;
            }

            public long getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(long releaseDate) {
                this.releaseDate = releaseDate;
            }

            public int getShareTagShow() {
                return shareTagShow;
            }

            public void setShareTagShow(int shareTagShow) {
                this.shareTagShow = shareTagShow;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getSubUrl() {
                return subUrl;
            }

            public void setSubUrl(String subUrl) {
                this.subUrl = subUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getWebImage() {
                return webImage;
            }

            public void setWebImage(String webImage) {
                this.webImage = webImage;
            }

            public String getWebUrl() {
                return webUrl;
            }

            public void setWebUrl(String webUrl) {
                this.webUrl = webUrl;
            }

            public static class LatestBangumiVideoBean {
                /**
                 * bangumiId : 1480038
                 * sort : 100
                 * title : 第10话
                 * updateTime : 1482140235000
                 * videoId : 4643853
                 */

                private int bangumiId;
                private int sort;
                private String title;
                private long updateTime;
                private int videoId;

                public int getBangumiId() {
                    return bangumiId;
                }

                public void setBangumiId(int bangumiId) {
                    this.bangumiId = bangumiId;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
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
            }

            public static class OwnerBean {
                /**
                 * avatar : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201611/16133520hzgcnklc.jpg
                 * id : 243400
                 * name : 鲁路修·VI布里塔尼亚
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
                 * comments : 484
                 * danmakuSize : 2628
                 * goldBanana : 9559
                 * goldBananaVoters : 0
                 * score : 0
                 * stows : 514
                 * ups : 0
                 * views : 154588
                 */

                private int comments;
                private int danmakuSize;
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
        }

        public static class MenusBean {
            /**
             * actionId : 6
             * channelId : 155
             * hide : 0
             * id : 4
             * image :
             * name : 更多番剧内容
             * regionId : 7
             * sort : 0
             * url :
             */

            private int actionId;
            private int channelId;
            private int hide;
            private int id;
            private String image;
            private String name;
            private int regionId;
            private int sort;
            private String url;

            public int getActionId() {
                return actionId;
            }

            public void setActionId(int actionId) {
                this.actionId = actionId;
            }

            public int getChannelId() {
                return channelId;
            }

            public void setChannelId(int channelId) {
                this.channelId = channelId;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRegionId() {
                return regionId;
            }

            public void setRegionId(int regionId) {
                this.regionId = regionId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
