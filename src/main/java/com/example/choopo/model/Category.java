package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="category")
public class Category {

    private long categoryId;

    @NotNull
    private int parentId;

    @NotBlank
    @Size(min = 1,max = 255, message = "CategoryName has exceeded the limit")
    private String categoryName;

//    @OneToOne(mappedBy="category", targetEntity=Article.class, fetch=FetchType.EAGER)
//    private Article article;

    public  Category(){

    }

    public Category(int parentId, String categoryName) {
        this.parentId = parentId;
        this.categoryName = categoryName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "parent_id", nullable = false)
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Column(name = "category_name", nullable = false)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", parentId=" + parentId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
