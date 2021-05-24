package com.example.choopo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "body_type")
public class  BodyType {

    private long bodyTypeId;

    @NotBlank
    @Size(min = 1,max = 255,message = "BodyTypeName has exceeded the limit")
    private String bodyTypeName;

    @NotNull
    private int bodyTypeCode;

    public BodyType(){

    }

    public BodyType(String bodyTypeName, int bodyTypeCode) {
        this.bodyTypeName = bodyTypeName;
        this.bodyTypeCode = bodyTypeCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "body_type_id", unique = true, nullable = false)
    public long getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(long bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    @Column(name = "body_type_name", nullable = false)
    public String getBodyTypeName() {
        return bodyTypeName;
    }

    public void setBodyTypeName(String bodyTypeName) {
        this.bodyTypeName = bodyTypeName;
    }

    @Column(name = "body_type_code", nullable = false)
    public int getBodyTypeCode() {
        return bodyTypeCode;
    }

    public void setBodyTypeCode(int bodyTypeCode) {
        this.bodyTypeCode = bodyTypeCode;
    }

    @Override
    public String toString() {
        return "BodyType{" +
                "bodyTypeId=" + bodyTypeId +
                ", bodyTypeName='" + bodyTypeName + '\'' +
                ", bodyTypeCode=" + bodyTypeCode +
                '}';
    }
}