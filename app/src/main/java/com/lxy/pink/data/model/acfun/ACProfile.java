package com.lxy.pink.data.model.acfun;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACProfile {

    /**
     * success : true
     * msg : ok
     * status : 200
     * data : {"fullUser":{"currExp":1400,"nextLevelNeed":1700,"banana":140,"bananaGold":0,"signature":"才、才不是懒得写签名呢！只是在思考呢！","level":12,"gender":1,"exp":1626,"mobile":null,"mobileCheck":0,"followed":0,"following":0,"qq":null,"lastLoginDate":0,"contributes":0,"username":"洛阳一枝花","userId":784105,"userImg":"http://cdn.aixifan.com/dotnet/20120923/style/image/avatar.jpg"}}
     */

    private boolean success;
    private String msg;
    private int status;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fullUser : {"currExp":1400,"nextLevelNeed":1700,"banana":140,"bananaGold":0,"signature":"才、才不是懒得写签名呢！只是在思考呢！","level":12,"gender":1,"exp":1626,"mobile":null,"mobileCheck":0,"followed":0,"following":0,"qq":null,"lastLoginDate":0,"contributes":0,"username":"洛阳一枝花","userId":784105,"userImg":"http://cdn.aixifan.com/dotnet/20120923/style/image/avatar.jpg"}
         */

        private FullUserBean fullUser;

        public FullUserBean getFullUser() {
            return fullUser;
        }

        public void setFullUser(FullUserBean fullUser) {
            this.fullUser = fullUser;
        }

        public static class FullUserBean {
            /**
             * currExp : 1400
             * nextLevelNeed : 1700
             * banana : 140
             * bananaGold : 0
             * signature : 才、才不是懒得写签名呢！只是在思考呢！
             * level : 12
             * gender : 1
             * exp : 1626
             * mobile : null
             * mobileCheck : 0
             * followed : 0
             * following : 0
             * qq : null
             * lastLoginDate : 0
             * contributes : 0
             * username : 洛阳一枝花
             * userId : 784105
             * userImg : http://cdn.aixifan.com/dotnet/20120923/style/image/avatar.jpg
             */

            private int currExp;
            private int nextLevelNeed;
            private int banana;
            private int bananaGold;
            private String signature;
            private int level;
            private int gender;
            private int exp;
            private Object mobile;
            private int mobileCheck;
            private int followed;
            private int following;
            private Object qq;
            private int lastLoginDate;
            private int contributes;
            private String username;
            private int userId;
            private String userImg;

            public int getCurrExp() {
                return currExp;
            }

            public void setCurrExp(int currExp) {
                this.currExp = currExp;
            }

            public int getNextLevelNeed() {
                return nextLevelNeed;
            }

            public void setNextLevelNeed(int nextLevelNeed) {
                this.nextLevelNeed = nextLevelNeed;
            }

            public int getBanana() {
                return banana;
            }

            public void setBanana(int banana) {
                this.banana = banana;
            }

            public int getBananaGold() {
                return bananaGold;
            }

            public void setBananaGold(int bananaGold) {
                this.bananaGold = bananaGold;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getExp() {
                return exp;
            }

            public void setExp(int exp) {
                this.exp = exp;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public int getMobileCheck() {
                return mobileCheck;
            }

            public void setMobileCheck(int mobileCheck) {
                this.mobileCheck = mobileCheck;
            }

            public int getFollowed() {
                return followed;
            }

            public void setFollowed(int followed) {
                this.followed = followed;
            }

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public Object getQq() {
                return qq;
            }

            public void setQq(Object qq) {
                this.qq = qq;
            }

            public int getLastLoginDate() {
                return lastLoginDate;
            }

            public void setLastLoginDate(int lastLoginDate) {
                this.lastLoginDate = lastLoginDate;
            }

            public int getContributes() {
                return contributes;
            }

            public void setContributes(int contributes) {
                this.contributes = contributes;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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
