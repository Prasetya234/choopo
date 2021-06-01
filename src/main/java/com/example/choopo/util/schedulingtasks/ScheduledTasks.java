package com.example.choopo.util.schedulingtasks;

import com.example.choopo.util.model.AuthenticationResponse;
import com.example.choopo.util.repository.AuthenticationResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private AuthenticationResponseRepository authenticationResponseRepository;

    @Scheduled(fixedDelay = 1000000)
    public void deleteToken() {
        List<AuthenticationResponse> expired = authenticationResponseRepository.findDateExpired(new Date());
        List<AuthenticationResponse> result=authenticationResponseRepository.findDateExpired(new Date());
        List<Object> arr= Arrays.asList(result);
        for(AuthenticationResponse a:result){
            if (expired == null) {
                return;
            } else {
                authenticationResponseRepository.delete(a);
            }
        }
    }
}
