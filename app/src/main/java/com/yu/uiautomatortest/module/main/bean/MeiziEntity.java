package com.yu.uiautomatortest.module.main.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-6-26.
 * 福利 http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1
 */

public class MeiziEntity {

    /**
     * error : false
     * results : [{"_id":"5941db7b421aa92c794633cd","createdAt":"2017-06-15T08:57:31.47Z","desc":"6-15","publishedAt":"2017-06-15T13:55:57.947Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fgllsthvu1j20u011in1p.jpg","used":true,"who":"代码家"}...]
     */

    public boolean error;
    public List<ResultsBean> results;


    public static class ResultsBean {
        /**
         * _id : 5941db7b421aa92c794633cd
         * createdAt : 2017-06-15T08:57:31.47Z
         * desc : 6-15
         * publishedAt : 2017-06-15T13:55:57.947Z
         * source : chrome
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/610dc034ly1fgllsthvu1j20u011in1p.jpg
         * used : true
         * who : 代码家
         */

        public String desc;
        public String url;
        public boolean used;
        public String who;

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "desc='" + desc + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MeiziEntity{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
