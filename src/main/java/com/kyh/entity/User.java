package com.kyh.entity;

/**
 * Created by kongyunhui on 2017/7/25.
 */
public class User {
    private String nickname;
    private String headimgurl;
    private int sex; // 1/0 - 男/女
    private String city;
    private String language;
    private long subscribe_time;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(long subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                ", language='" + language + '\'' +
                ", subscribe_time=" + subscribe_time +
                '}';
    }
}
