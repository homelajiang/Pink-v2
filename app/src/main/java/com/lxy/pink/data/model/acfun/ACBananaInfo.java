package com.lxy.pink.data.model.acfun;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACBananaInfo {

    /**
     * success : true
     * result : 操作成功
     * info : 操作成功
     * status : 200
     * data : {"bananaGain":99,"bananaGold":0,"bananaCost":0,"bananaCount":99}
     */

    private boolean success;
    private String result;
    private String info;
    private int status;
    private DataEntity data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        /**
         * bananaGain : 99
         * bananaGold : 0
         * bananaCost : 0
         * bananaCount : 99
         */

        private int bananaGain;
        private int bananaGold;
        private int bananaCost;
        private int bananaCount;

        public int getBananaGain() {
            return bananaGain;
        }

        public void setBananaGain(int bananaGain) {
            this.bananaGain = bananaGain;
        }

        public int getBananaGold() {
            return bananaGold;
        }

        public void setBananaGold(int bananaGold) {
            this.bananaGold = bananaGold;
        }

        public int getBananaCost() {
            return bananaCost;
        }

        public void setBananaCost(int bananaCost) {
            this.bananaCost = bananaCost;
        }

        public int getBananaCount() {
            return bananaCount;
        }

        public void setBananaCount(int bananaCount) {
            this.bananaCount = bananaCount;
        }
    }
}
