package com.kyh.message.resp;

import java.util.List;

/**
 * 图文消息（普通消息）
 */
public class NewsMessage extends BaseMessage {
    // 图文消息个数，限制为 10 条以内   
    private int ArticleCount;  
    // 多条图文消息信息，默认第一个 item 为大图   
    private List<Article> Articles;

    public NewsMessage() {
    }

    public NewsMessage(String toUserName, String fromUserName, long createTime, String msgType, int articleCount, List<Article> articles) {
        if(articleCount>10) throw new IllegalArgumentException("图文回复消息个数非法，请核实！");

        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.setMsgType(msgType);
        ArticleCount = articleCount;
        Articles = articles;
    }

    public int getArticleCount() {
        return ArticleCount;
    }  

    public void setArticleCount(int articleCount) {  
        ArticleCount = articleCount;  
    }  

    public List<Article> getArticles() {  
        return Articles;  
    }  

    public void setArticles(List<Article> articles) {  
        Articles = articles;  
    }

    /**
     * 静态内部类，又称静态嵌套类。其不依赖于外部类(不使用外部类的非静态属性和方法)，仅仅是借用其外壳"隐藏"一下自己，方便管理类结构而定义。
     *
     * 单个图文消息实体类
     */
    public static class Article{
        // 图文消息名称
        private String Title;
        // 图文消息描述
        private String Description;
        // 图片链接，支持 JPG、PNG 格式，较好的效果为大图 640*320，小图 80*80，
        private String PicUrl;
        // 点击图文消息跳转链接
        private String Url;

        public Article() {
        }

        public Article(String title, String description, String picUrl, String url) {
            Title = title;
            Description = description;
            PicUrl = picUrl;
            Url = url;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return null == Description ? "" : Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getPicUrl() {
            return null == PicUrl ? "" : PicUrl;
        }

        public void setPicUrl(String picUrl) {
            PicUrl = picUrl;
        }

        public String getUrl() {
            return null == Url ? "" : Url;
        }

        public void setUrl(String url) {
            Url = url;
        }
    }
}