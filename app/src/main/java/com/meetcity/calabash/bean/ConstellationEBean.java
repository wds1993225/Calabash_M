package com.meetcity.calabash.bean;

/**
 * Created by wds1993225 on 2016/9/5.
 */
public class ConstellationEBean {
    /**
     * 幸运颜色 : 梅子青
     * 工作状况 : 4
     * 财运 : 有钱可赚，但颇为艰辛。

     * 星运解读 : 走出家门，与朋友相聚可为你带来好运。穿着色彩明亮的服饰可增强桃花运，有异性主动示好，对感觉不错的对象不妨大胆表白，会得到令你满意的回应。需要花钱的地方较多，做好预算，以免透支。
     * 本月劣势 : --
     * type : libra
     * 综合运势 : 4
     * 速配星座 : 双鱼座
     * 健康指数 : 60%
     * 理财投资 : 3
     * source : {"title":"紫微星座网","link":"http://m.go108.com.cn?from=zhwnl"}
     * 爱情运势 : 4
     * 本月优势 : --
     * day : 20160904
     * 开运方位 : --
     * 爱情运 : 适合谈情说爱，恋爱中的人不妨多安排约会，让彼此都感受到对方的情意绵绵。
     * 事业运 : 有时候听听家人朋友的意见，会让你豁然开朗，还能想通一些困扰你多时的问题喔！
     * 幸运数字 : 6
     * 幸运月份 : --
     */

    private String 幸运颜色;
    private String 工作状况;
    private String 财运;
    private String 星运解读;
    private String 本月劣势;
    private String type;
    private String 综合运势;
    private String 速配星座;
    private String 健康指数;
    private String 理财投资;
    /**
     * title : 紫微星座网
     * link : http://m.go108.com.cn?from=zhwnl
     */

    private SourceBean source;
    private String 爱情运势;
    private String 本月优势;
    private String day;
    private String 开运方位;
    private String 爱情运;
    private String 事业运;
    private String 幸运数字;
    private String 幸运月份;

    public String get幸运颜色() {
        return 幸运颜色;
    }

    public void set幸运颜色(String 幸运颜色) {
        this.幸运颜色 = 幸运颜色;
    }

    public String get工作状况() {
        return 工作状况;
    }

    public void set工作状况(String 工作状况) {
        this.工作状况 = 工作状况;
    }

    public String get财运() {
        return 财运;
    }

    public void set财运(String 财运) {
        this.财运 = 财运;
    }

    public String get星运解读() {
        return 星运解读;
    }

    public void set星运解读(String 星运解读) {
        this.星运解读 = 星运解读;
    }

    public String get本月劣势() {
        return 本月劣势;
    }

    public void set本月劣势(String 本月劣势) {
        this.本月劣势 = 本月劣势;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String get综合运势() {
        return 综合运势;
    }

    public void set综合运势(String 综合运势) {
        this.综合运势 = 综合运势;
    }

    public String get速配星座() {
        return 速配星座;
    }

    public void set速配星座(String 速配星座) {
        this.速配星座 = 速配星座;
    }

    public String get健康指数() {
        return 健康指数;
    }

    public void set健康指数(String 健康指数) {
        this.健康指数 = 健康指数;
    }

    public String get理财投资() {
        return 理财投资;
    }

    public void set理财投资(String 理财投资) {
        this.理财投资 = 理财投资;
    }

    public SourceBean getSource() {
        return source;
    }

    public void setSource(SourceBean source) {
        this.source = source;
    }

    public String get爱情运势() {
        return 爱情运势;
    }

    public void set爱情运势(String 爱情运势) {
        this.爱情运势 = 爱情运势;
    }

    public String get本月优势() {
        return 本月优势;
    }

    public void set本月优势(String 本月优势) {
        this.本月优势 = 本月优势;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String get开运方位() {
        return 开运方位;
    }

    public void set开运方位(String 开运方位) {
        this.开运方位 = 开运方位;
    }

    public String get爱情运() {
        return 爱情运;
    }

    public void set爱情运(String 爱情运) {
        this.爱情运 = 爱情运;
    }

    public String get事业运() {
        return 事业运;
    }

    public void set事业运(String 事业运) {
        this.事业运 = 事业运;
    }

    public String get幸运数字() {
        return 幸运数字;
    }

    public void set幸运数字(String 幸运数字) {
        this.幸运数字 = 幸运数字;
    }

    public String get幸运月份() {
        return 幸运月份;
    }

    public void set幸运月份(String 幸运月份) {
        this.幸运月份 = 幸运月份;
    }

    public static class SourceBean {
        private String title;
        private String link;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
