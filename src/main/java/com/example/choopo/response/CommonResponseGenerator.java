package com.example.choopo.response;

import org.springframework.stereotype.Component;

@Component
public class CommonResponseGenerator<T> {

    public <T> CommonResponse<T> successResponse(T content) {
        CommonResponse commonResponse = new CommonResponse<>();
        commonResponse.setStatus("200");
        commonResponse.setMessage("SUCCESS");
        commonResponse.setContet(content);

        return commonResponse;
    }
}
