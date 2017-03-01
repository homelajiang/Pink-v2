package com.lxy.pink.data.model.acfun;

import java.util.List;
import java.util.Map;

/**
 * Created by homelajiang on 2017/3/1 0001.
 */

public class ACVideoCommentData {

    /**
     * success : true
     * msg : 操作成功
     * status : 200
     */

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

        private String cache;
        private PageEntity page;

        public String getCache() {
            return cache;
        }

        public void setCache(String cache) {
            this.cache = cache;
        }

        public PageEntity getPage() {
            return page;
        }

        public void setPage(PageEntity page) {
            this.page = page;
        }

        public static class PageEntity {

            private int totalCount;
            private int pageSize;
            private int pageNo;
            private Map<String, ACVideoComment> map;
            private List<Integer> list;

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public Map<String, ACVideoComment> getMap() {
                return map;
            }

            public void setMap(Map<String, ACVideoComment> map) {
                this.map = map;
            }

            public List<Integer> getList() {
                return list;
            }

            public void setList(List<Integer> list) {
                this.list = list;
            }

        }
    }
}
