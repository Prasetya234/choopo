package com.example.choopo.model;


import javax.persistence.*;

@Entity
@Table(name="body")
public class Body {
    private long bodyId;

    private String bodyContent;

    private String articleId;

    private String bodyType;

    private BodyType bodyTypeId;

    public Body(){

    }

    public Body(String bodyContent, String articleId, String bodyType) {
        this.bodyContent = bodyContent;
        this.articleId = articleId;
        this.bodyType = bodyType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_id")
    public long getBodyId() {
        return bodyId;
    }

    public void setBodyId(long bodyId) {
        this.bodyId = bodyId;
    }

    @Column(name = "body_content", nullable = false)
    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    @Column(name = "article_id" , nullable = false)
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Transient
    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
    @JoinColumn(name = "body_type_id", nullable = true)
    public BodyType getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(BodyType bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    @Override
    public String toString() {
        return "Body{" +
                "bodyId=" + bodyId +
                ", bodyContent='" + bodyContent + '\'' +
                ", articleId='" + articleId + '\'' +
                '}';
    }
}