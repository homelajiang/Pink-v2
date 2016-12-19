package com.lxy.pink.data.model.acfun;

/**
 * Created by yuan on 2016/12/19.
 */

public class ACSplash {

    /**
     * code : 200
     * data : {"gameEntrance":{"updateDate":1481792144000,"newGameStartTime":1479957624000,"endDate":1482318037000,"openSource":"http://m.acfun.tv/game/list?referrer=icon","pic":"http://cdn.aixifan.com/acfun-H5/public/images/games/200x200.png","type":4,"newGameEndTime":1480607724000,"txt":"","newGameUrl":"http://m.acfun.tv/game/list?referrer=new","id":333,"startDate":1476343237000},"flash":{"txt":"","updateDate":1481700912000,"endDate":1482487243000,"openSource":"","id":331,"pic":"http://imgs.aixifan.com/cms/2016_11_14/1479092004794.jpg","type":9,"startDate":1479956776000}}
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
         * gameEntrance : {"updateDate":1481792144000,"newGameStartTime":1479957624000,"endDate":1482318037000,"openSource":"http://m.acfun.tv/game/list?referrer=icon","pic":"http://cdn.aixifan.com/acfun-H5/public/images/games/200x200.png","type":4,"newGameEndTime":1480607724000,"txt":"","newGameUrl":"http://m.acfun.tv/game/list?referrer=new","id":333,"startDate":1476343237000}
         * flash : {"txt":"","updateDate":1481700912000,"endDate":1482487243000,"openSource":"","id":331,"pic":"http://imgs.aixifan.com/cms/2016_11_14/1479092004794.jpg","type":9,"startDate":1479956776000}
         */

        private GameEntranceBean gameEntrance;
        private FlashBean flash;

        public GameEntranceBean getGameEntrance() {
            return gameEntrance;
        }

        public void setGameEntrance(GameEntranceBean gameEntrance) {
            this.gameEntrance = gameEntrance;
        }

        public FlashBean getFlash() {
            return flash;
        }

        public void setFlash(FlashBean flash) {
            this.flash = flash;
        }

        public static class GameEntranceBean {
            /**
             * updateDate : 1481792144000
             * newGameStartTime : 1479957624000
             * endDate : 1482318037000
             * openSource : http://m.acfun.tv/game/list?referrer=icon
             * pic : http://cdn.aixifan.com/acfun-H5/public/images/games/200x200.png
             * type : 4
             * newGameEndTime : 1480607724000
             * txt :
             * newGameUrl : http://m.acfun.tv/game/list?referrer=new
             * id : 333
             * startDate : 1476343237000
             */

            private long updateDate;
            private long newGameStartTime;
            private long endDate;
            private String openSource;
            private String pic;
            private int type;
            private long newGameEndTime;
            private String txt;
            private String newGameUrl;
            private int id;
            private long startDate;

            public long getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(long updateDate) {
                this.updateDate = updateDate;
            }

            public long getNewGameStartTime() {
                return newGameStartTime;
            }

            public void setNewGameStartTime(long newGameStartTime) {
                this.newGameStartTime = newGameStartTime;
            }

            public long getEndDate() {
                return endDate;
            }

            public void setEndDate(long endDate) {
                this.endDate = endDate;
            }

            public String getOpenSource() {
                return openSource;
            }

            public void setOpenSource(String openSource) {
                this.openSource = openSource;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getNewGameEndTime() {
                return newGameEndTime;
            }

            public void setNewGameEndTime(long newGameEndTime) {
                this.newGameEndTime = newGameEndTime;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getNewGameUrl() {
                return newGameUrl;
            }

            public void setNewGameUrl(String newGameUrl) {
                this.newGameUrl = newGameUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getStartDate() {
                return startDate;
            }

            public void setStartDate(long startDate) {
                this.startDate = startDate;
            }
        }

        public static class FlashBean {
            /**
             * txt :
             * updateDate : 1481700912000
             * endDate : 1482487243000
             * openSource :
             * id : 331
             * pic : http://imgs.aixifan.com/cms/2016_11_14/1479092004794.jpg
             * type : 9
             * startDate : 1479956776000
             */

            private String txt;
            private long updateDate;
            private long endDate;
            private String openSource;
            private int id;
            private String pic;
            private int type;
            private long startDate;

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public long getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(long updateDate) {
                this.updateDate = updateDate;
            }

            public long getEndDate() {
                return endDate;
            }

            public void setEndDate(long endDate) {
                this.endDate = endDate;
            }

            public String getOpenSource() {
                return openSource;
            }

            public void setOpenSource(String openSource) {
                this.openSource = openSource;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getStartDate() {
                return startDate;
            }

            public void setStartDate(long startDate) {
                this.startDate = startDate;
            }
        }
    }
}
