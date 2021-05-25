package com.example.choopo.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserTypeDTO {

        private long userTypeId;

        @NotBlank
        @Size(min = 1, max = 255, message = "user_type_name has exceeded the limit")
        private String userTypeName;

        @NotNull
        private int userTypeCode;

        // GET & SET
        public long getUserTypeId() {
            return userTypeId;
        }

        public void setUserTypeId(long userTypeId) {
            this.userTypeId = userTypeId;
        }

        public String getUserTypeName() {
            return userTypeName;
        }

        public void setUserTypeName(String userTypeName) {
            this.userTypeName = userTypeName;
        }

        public int getUserTypeCode() {
            return userTypeCode;
        }

        public void setUserTypeCode(int userTypeCode) {
            this.userTypeCode = userTypeCode;
        }
}
