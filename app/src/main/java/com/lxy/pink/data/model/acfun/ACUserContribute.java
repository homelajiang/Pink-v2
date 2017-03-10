package com.lxy.pink.data.model.acfun;

import java.util.List;

/**
 * Created by homelajiang on 2017/3/10 0010.
 */

public class ACUserContribute {

    private boolean success;
    private String msg;
    private int status;
    private DataEntity data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {

        private PageEntity page;

        public PageEntity getPage() {
            return page;
        }

        public void setPage(PageEntity page) {
            this.page = page;
        }

        public static class PageEntity {

            private int pageNo;
            private int pageSize;
            private int totalCount;
            private int orderBy;
            private List<ListEntity> list;

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(int orderBy) {
                this.orderBy = orderBy;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public static class ListEntity {
                /**
                 * contentId : 3078764
                 * title : 【东方·MMD】巫女桑告诉我
                 * cover : http://imgs.aixifan.com/content/2016_09_04/1472999885.jpg
                 * description : 作者：うすた様<br/>策划/后期：VV<br/>字幕/压制：东方日常字幕组 帕瓦双子<br/>||若侵则删||
                 * comments : 3
                 * views : 6519
                 * specialId : 0
                 * releaseDate : 1473000529000
                 * stows : 394
                 * contentSize : null
                 * time : null
                 * channelId : 108
                 * user : {"userId":614361,"username":"poorich","userImg":"http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201609/20210909j25k0j3f.jpg"}
                 */

                private int contentId;
                private String title;
                private String cover;
                private String description;
                private int comments;
                private int views;
                private int specialId;
                private long releaseDate;
                private int stows;
                private Object contentSize;
                private Object time;
                private int channelId;
                private UserEntity user;

                public int getContentId() {
                    return contentId;
                }

                public void setContentId(int contentId) {
                    this.contentId = contentId;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
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

                public int getComments() {
                    return comments;
                }

                public void setComments(int comments) {
                    this.comments = comments;
                }

                public int getViews() {
                    return views;
                }

                public void setViews(int views) {
                    this.views = views;
                }

                public int getSpecialId() {
                    return specialId;
                }

                public void setSpecialId(int specialId) {
                    this.specialId = specialId;
                }

                public long getReleaseDate() {
                    return releaseDate;
                }

                public void setReleaseDate(long releaseDate) {
                    this.releaseDate = releaseDate;
                }

                public int getStows() {
                    return stows;
                }

                public void setStows(int stows) {
                    this.stows = stows;
                }

                public Object getContentSize() {
                    return contentSize;
                }

                public void setContentSize(Object contentSize) {
                    this.contentSize = contentSize;
                }

                public Object getTime() {
                    return time;
                }

                public void setTime(Object time) {
                    this.time = time;
                }

                public int getChannelId() {
                    return channelId;
                }

                public void setChannelId(int channelId) {
                    this.channelId = channelId;
                }

                public UserEntity getUser() {
                    return user;
                }

                public void setUser(UserEntity user) {
                    this.user = user;
                }

                public static class UserEntity {
                    /**
                     * userId : 614361
                     * username : poorich
                     * userImg : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201609/20210909j25k0j3f.jpg
                     */

                    private int userId;
                    private String username;
                    private String userImg;

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

                    public String getUserImg() {
                        return userImg;
                    }

                    public void setUserImg(String userImg) {
                        this.userImg = userImg;
                    }
                }
            }
        }
    }
}
