package com.meetcity.calabash.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wds1993225 on 2016/9/23.
 */
public class DouBean {

    private int count;
    private int offset;
    private String date;
    private int total;
    /**
     * display_style : 10002
     * is_editor_choice : false
     * published_time : 2016-08-11 22:00:00
     * original_url :
     * url : https://moment.douban.com/post/144624/?douban_rec=1
     * short_url : https://dou.bz/1QawCH
     * is_liked : false
     * column : 洗洗睡
     * app_css : 7
     * abstract : 无论我们如何地热衷于在碎玻璃上跳舞，总有些门不该被打开，总有些故事刺痛喉咙像烧着烧着就熄灭了的火，心碎的人并不畏惧人群……
     * date : 2016-08-11
     * like_count : 319
     * comments_count : 52
     * thumbs : [{"medium":{"url":"https://img1.doubanio.com/view/presto/medium/public/t118439.jpg","width":460,"height":689},"description":"","large":{"url":"https://img1.doubanio.com/view/presto/large/public/t118439.jpg","width":460,"height":689},"tag_name":"img_1","small":{"url":"https://img1.doubanio.com/view/presto/small/public/t118439.jpg","width":320,"height":479},"id":118439}]
     * created_time : 2016-08-11 14:26:26
     * title : 洗洗睡｜事物的关联令人心碎
     * share_pic_url : https://moment.douban.com/share_pic/post/144624.jpg
     * type : 1001
     * id : 144624
     */

    private List<PostsBean> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class PostsBean {
        private int display_style;
        private boolean is_editor_choice;
        private String published_time;
        private String original_url;
        private String url;
        private String short_url;
        private boolean is_liked;
        private String column;
        private int app_css;
        @SerializedName("abstract")
        private String abstractX;
        private String date;
        private int like_count;
        private int comments_count;
        private String created_time;
        private String title;
        private String share_pic_url;
        private String type;
        private int id;
        /**
         * medium : {"url":"https://img1.doubanio.com/view/presto/medium/public/t118439.jpg","width":460,"height":689}
         * description :
         * large : {"url":"https://img1.doubanio.com/view/presto/large/public/t118439.jpg","width":460,"height":689}
         * tag_name : img_1
         * small : {"url":"https://img1.doubanio.com/view/presto/small/public/t118439.jpg","width":320,"height":479}
         * id : 118439
         */

        private List<ThumbsBean> thumbs;

        public int getDisplay_style() {
            return display_style;
        }

        public void setDisplay_style(int display_style) {
            this.display_style = display_style;
        }

        public boolean isIs_editor_choice() {
            return is_editor_choice;
        }

        public void setIs_editor_choice(boolean is_editor_choice) {
            this.is_editor_choice = is_editor_choice;
        }

        public String getPublished_time() {
            return published_time;
        }

        public void setPublished_time(String published_time) {
            this.published_time = published_time;
        }

        public String getOriginal_url() {
            return original_url;
        }

        public void setOriginal_url(String original_url) {
            this.original_url = original_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getShort_url() {
            return short_url;
        }

        public void setShort_url(String short_url) {
            this.short_url = short_url;
        }

        public boolean isIs_liked() {
            return is_liked;
        }

        public void setIs_liked(boolean is_liked) {
            this.is_liked = is_liked;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public int getApp_css() {
            return app_css;
        }

        public void setApp_css(int app_css) {
            this.app_css = app_css;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShare_pic_url() {
            return share_pic_url;
        }

        public void setShare_pic_url(String share_pic_url) {
            this.share_pic_url = share_pic_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<ThumbsBean> getThumbs() {
            return thumbs;
        }

        public void setThumbs(List<ThumbsBean> thumbs) {
            this.thumbs = thumbs;
        }

        public static class ThumbsBean {
            /**
             * url : https://img1.doubanio.com/view/presto/medium/public/t118439.jpg
             * width : 460
             * height : 689
             */

            private MediumBean medium;
            private String description;
            /**
             * url : https://img1.doubanio.com/view/presto/large/public/t118439.jpg
             * width : 460
             * height : 689
             */

            private LargeBean large;
            private String tag_name;
            /**
             * url : https://img1.doubanio.com/view/presto/small/public/t118439.jpg
             * width : 320
             * height : 479
             */

            private SmallBean small;
            private int id;

            public MediumBean getMedium() {
                return medium;
            }

            public void setMedium(MediumBean medium) {
                this.medium = medium;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public LargeBean getLarge() {
                return large;
            }

            public void setLarge(LargeBean large) {
                this.large = large;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public SmallBean getSmall() {
                return small;
            }

            public void setSmall(SmallBean small) {
                this.small = small;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class MediumBean {
                private String url;
                private int width;
                private int height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public static class LargeBean {
                private String url;
                private int width;
                private int height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public static class SmallBean {
                private String url;
                private int width;
                private int height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }
    }
}
