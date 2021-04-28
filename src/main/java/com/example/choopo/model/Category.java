package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="category")
public class Category {

    private long category_id;

    @NotNull
    private int parent_id;

    @NotNull
    @Size(min = 1,max = 255, message = "CategoryName has exceeded the limit")
    private String category_name;

    public  Category(){

    }

    public Category(int parent_id, String category_name) {
        this.parent_id = parent_id;
        this.category_name = category_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    @Column(name = "parent_id", nullable = false)
    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    @Column(name = "category_name", nullable = false)
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", parent_id=" + parent_id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}
