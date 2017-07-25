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

    public NewsMessage(String toUserName, String fromUserName, long createTime, String msgType, List<Article> articles) {
        if(articles.size()>10) throw new IllegalArgumentException("图文回复消息个数非法，请核实！");

        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.setMsgType(msgType);
        this.setArticleCount(articles.size());
        this.setArticles(articles);
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
}