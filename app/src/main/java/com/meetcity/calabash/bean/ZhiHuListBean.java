package com.meetcity.calabash.bean;

import java.util.List;

/**
 * Created by wds1993225 on 2016/8/30.
 */
public class ZhiHuListBean {

    /**
     * date : 20160830
     * stories : [{"images":["http://pic1.zhimg.com/ef128c49268ae8d83b5ea5436f45e6e8.jpg"],"type":0,"id":8736843,"ga_prefix":"083016","title":"世上没有最好的食用油，只有最合理的搭配方法"},{"images":["http://pic4.zhimg.com/c8fa4a1f4992ce0cdf77be0e217f2b17.jpg"],"type":0,"id":8741854,"ga_prefix":"083015","title":"「I」 will always be with 「U」，是物理老师的谎言"},{"title":"在野外被犀牛追击，来做科考的我们好像进了侏罗纪公园","ga_prefix":"083014","images":["http://pic2.zhimg.com/d72d9a871896835f9c5e86f685e9aaf1.jpg"],"multipic":true,"type":0,"id":8741923},{"images":["http://pic2.zhimg.com/46ea5ca3736ebf4cfeeaef2947242785.jpg"],"type":0,"id":8733818,"ga_prefix":"083013","title":"饭又蒸多了？没关系，可以做成外焦里嫩的五平饼"},{"images":["http://pic3.zhimg.com/c067a74b9c3bbc099e934337268de95e.jpg"],"type":0,"id":8733087,"ga_prefix":"083012","title":"大误 · 救活我的「厉鬼」女友"},{"images":["http://pic3.zhimg.com/2511a9bebb54e8ff45a4858cdca9ea6a.jpg"],"type":0,"id":8739849,"ga_prefix":"083011","title":"自媒体读者行为洞察报告：教你办一个优质的公众号"},{"images":["http://pic4.zhimg.com/5baad8cabc7468b74b01229604bd0e73.jpg"],"type":0,"id":8739884,"ga_prefix":"083010","title":"摆满货架的快消巨头，是如何失去了自己的黄金时代"},{"images":["http://pic1.zhimg.com/533f8ae35cce0e436dc354afbb89d5dc.jpg"],"type":0,"id":8739516,"ga_prefix":"083009","title":"如果没有这项 DNA 技术，许多连环杀人案也许仍是悬案"},{"images":["http://pic3.zhimg.com/e78005ef1e2c063bfb183822f3c3dbb6.jpg"],"type":0,"id":8738630,"ga_prefix":"083008","title":"被「包养」的女性和家庭妇女相比，本质区别是什么？"},{"images":["http://pic4.zhimg.com/4d2333f9caeab06802eb147c05e31a23.jpg"],"type":0,"id":8739499,"ga_prefix":"083007","title":"巴菲特的持仓信息都公开了，那跟着买岂不是稳赚？"},{"images":["http://pic2.zhimg.com/f3aed469c6f030847552e25048747add.jpg"],"type":0,"id":8736007,"ga_prefix":"083007","title":"「随大流」买买买选选选，有时候还挺明智的"},{"images":["http://pic3.zhimg.com/e25d3f69c00ef0e2936b9bebc923212a.jpg"],"type":0,"id":8739332,"ga_prefix":"083007","title":"水干不干净，关系到收入水平"},{"images":["http://pic3.zhimg.com/49eed1acb470cad9ba88b9c0de7dce3e.jpg"],"type":0,"id":8740755,"ga_prefix":"083007","title":"读读日报 24 小时热门 TOP 5 · 关于期权的若干硬知识"},{"images":["http://pic4.zhimg.com/dc67acf85c26be119a4c141c156653fb.jpg"],"type":0,"id":8736197,"ga_prefix":"083006","title":"瞎扯 · 如何正确地吐槽"},{"images":["http://pic4.zhimg.com/d8e7c6d05dff0ec97c53a7ea9fcac527.jpg"],"type":0,"id":8713641,"ga_prefix":"083006","title":"这里是广告 · 哄脚指南"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/57c2640fb82faffa96efb660fd9f1997.jpg","type":0,"id":8741923,"ga_prefix":"083014","title":"在野外被犀牛追击，来做科考的我们好像进了侏罗纪公园"},{"image":"http://pic3.zhimg.com/6c042fc9422fdd038c453f1a81d79752.jpg","type":0,"id":8739516,"ga_prefix":"083009","title":"如果没有这项 DNA 技术，许多连环杀人案也许仍是悬案"},{"image":"http://pic2.zhimg.com/7b87f0e815fd3bd3222de5d8ead36421.jpg","type":0,"id":8739499,"ga_prefix":"083007","title":"巴菲特的持仓信息都公开了，那跟着买岂不是稳赚？"},{"image":"http://pic4.zhimg.com/a8d1dedefb291fbbdf0801f8d5311fb3.jpg","type":0,"id":8736007,"ga_prefix":"083007","title":"「随大流」买买买选选选，有时候还挺明智的"},{"image":"http://pic3.zhimg.com/7b4ad9876f09d94e100c4939eb8f5f52.jpg","type":0,"id":8740755,"ga_prefix":"083007","title":"读读日报 24 小时热门 TOP 5 · 关于期权的若干硬知识"}]
     */

    private String date;
    /**
     * images : ["http://pic1.zhimg.com/ef128c49268ae8d83b5ea5436f45e6e8.jpg"]
     * type : 0
     * id : 8736843
     * ga_prefix : 083016
     * title : 世上没有最好的食用油，只有最合理的搭配方法
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic4.zhimg.com/57c2640fb82faffa96efb660fd9f1997.jpg
     * type : 0
     * id : 8741923
     * ga_prefix : 083014
     * title : 在野外被犀牛追击，来做科考的我们好像进了侏罗纪公园
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
