package com.meetcity.calabash.bean;

import java.util.List;

/**
 * Created by wds1993225 on 2016/9/20.
 */
public class YiBean {



    private int res;
    /**
     * hpcontent_id : 1472
     * hp_title : VOL.1444
     * author_id : -1
     * hp_img_url : http://image.wufazhuce.com/FlfuxmKEt4_8ZSiPe4vglgjP9WGH
     * hp_img_original_url : http://image.wufazhuce.com/FlfuxmKEt4_8ZSiPe4vglgjP9WGH
     * hp_author : 阳光照进回忆&@黑桃花椒 作品
     * ipad_url : http://image.wufazhuce.com/Fh_HfBN3jsukDPQTCgVsiuR4KwkQ
     * hp_content : 梦，真是人活着最奇怪的东西，那么私密亲切到仿佛跟自己都不好透露，可又遥远恍惚得好像跟你没相干，是个陌生人的造访。by 唐诺
     * hp_makettime : 2016-09-19 21:00:00
     * last_update_date : 2016-09-17 18:33:56
     * web_url : http://m.wufazhuce.com/one/1472
     * wb_img_url :
     * praisenum : 10688
     * sharenum : 1328
     * commentnum : 0
     */

    private List<DataBean> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String hpcontent_id;
        private String hp_title;
        private String author_id;
        private String hp_img_url;
        private String hp_img_original_url;
        private String hp_author;
        private String ipad_url;
        private String hp_content;
        private String hp_makettime;
        private String last_update_date;
        private String web_url;
        private String wb_img_url;
        private int praisenum;
        private int sharenum;
        private int commentnum;

        public String getHpcontent_id() {
            return hpcontent_id;
        }

        public void setHpcontent_id(String hpcontent_id) {
            this.hpcontent_id = hpcontent_id;
        }

        public String getHp_title() {
            return hp_title;
        }

        public void setHp_title(String hp_title) {
            this.hp_title = hp_title;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getHp_img_url() {
            return hp_img_url;
        }

        public void setHp_img_url(String hp_img_url) {
            this.hp_img_url = hp_img_url;
        }

        public String getHp_img_original_url() {
            return hp_img_original_url;
        }

        public void setHp_img_original_url(String hp_img_original_url) {
            this.hp_img_original_url = hp_img_original_url;
        }

        public String getHp_author() {
            return hp_author;
        }

        public void setHp_author(String hp_author) {
            this.hp_author = hp_author;
        }

        public String getIpad_url() {
            return ipad_url;
        }

        public void setIpad_url(String ipad_url) {
            this.ipad_url = ipad_url;
        }

        public String getHp_content() {
            return hp_content;
        }

        public void setHp_content(String hp_content) {
            this.hp_content = hp_content;
        }

        public String getHp_makettime() {
            return hp_makettime;
        }

        public void setHp_makettime(String hp_makettime) {
            this.hp_makettime = hp_makettime;
        }

        public String getLast_update_date() {
            return last_update_date;
        }

        public void setLast_update_date(String last_update_date) {
            this.last_update_date = last_update_date;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getWb_img_url() {
            return wb_img_url;
        }

        public void setWb_img_url(String wb_img_url) {
            this.wb_img_url = wb_img_url;
        }

        public int getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(int praisenum) {
            this.praisenum = praisenum;
        }

        public int getSharenum() {
            return sharenum;
        }

        public void setSharenum(int sharenum) {
            this.sharenum = sharenum;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }
    }
}
