package com.example.choopo.web.response;

import org.springframework.stereotype.Component;

@Component
public class CommonResponseGenerator<T> {

    public <T> com.example.choopo.web.response.CommonResponse successResponse(T content) {
        CommonResponse commonResponse = new CommonResponse<>();
        commonResponse.setStatus("200");
        commonResponse.setMessage("SUCCESS");
        commonResponse.setContet(content);

        return commonResponse;
    }
}
