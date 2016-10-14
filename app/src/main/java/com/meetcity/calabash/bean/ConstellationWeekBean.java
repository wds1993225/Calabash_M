package com.meetcity.calabash.bean;

/**
 * Created by wds1993225 on 2016/8/23.
 */
public class ConstellationWeekBean {
    /**
     * date : 2016年08月21日-2016年08月27日
     * name : 白羊座
     * health : 健康：富贵病出没季节。 作者：马子晴
     * job : 求职：机会不错，抓紧把握，理由同上。
     * love : 恋情：计较令你伤神。碎碎念没有用的，世界这么美，你应该去看看。
     * money : 财运：加薪空间开始缩小。建议提前做好预算计划，应对下周开启的水逆。
     * weekth : 35
     * work : 工作：太阳进入工作宫，预示着工作任务的加重。不过本周还好，你对手头项目有着兴趣和热情。但子晴建议尽快完成，因为下周的星象不太乐观了。
     * resultcode : 200
     * error_code : 0
     */

    private String date;
    private String name;
    private String health;
    private String job;
    private String love;
    private String money;
    private int weekth;
    private String work;
    private String resultcode;
    private int error_code;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getWeekth() {
        return weekth;
    }

    public void setWeekth(int weekth) {
        this.weekth = weekth;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
