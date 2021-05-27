package com.example.choopo.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="category")
public class Category {

    private long categoryId;

    private int parentId;

    private String categoryName;

    public  Category(){

    }

    public Category(int parentId, String categoryName) {
        this.parentId = parentId;
        this.categoryName = categoryName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_category")
    @SequenceGenerator(name = "auto_category", sequenceName = "category_id")
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
