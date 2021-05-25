package com.example.choopo.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BodyTypeDTO {

    private long bodyTypeId;

    @NotBlank
    @Size(min = 1,max = 255,message = "BodyTypeName has exceeded the limit")
    private String bodyTypeName;

    @NotNull
    private int bodyTypeCode;

    //GET & SET
    public long getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(long bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public String getBodyTypeName() {
        return bodyTypeName;
    }

    public void setBodyTypeName(String bodyTypeName) {
        this.bodyTypeName = bodyTypeName;
    }

    public int getBodyTypeCode() {
        return bodyTypeCode;
    }

    public void setBodyTypeCode(int bodyTypeCode) {
        this.bodyTypeCode = bodyTypeCode;
    }
}
