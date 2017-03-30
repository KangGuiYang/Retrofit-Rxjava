package com.iseeyou.kang.netframework.bean;

/**
 * Created by kGod on 2017/3/22.
 * Email 18252032703@163.com
 * Thank you for watching my code
 * 引导页实体类
 */

public class GuideBean {


    public static class ResBean {
        /**
         * businessType : 0
         * createTime : 1490079800000
         * id : 4
         * name : abadou
         * status : 1
         * type : 1
         * url : http://120.77.251.141:8081/youe/c03.png
         */

        private int businessType;
        private long createTime;
        private int id;
        private String name;
        private int status;
        private int type;
        private String url;

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ResBean{" +
                    "businessType=" + businessType +
                    ", createTime=" + createTime +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", status=" + status +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
