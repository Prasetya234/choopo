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

    //86400000 = 1 Day
    @Scheduled(fixedDelay = 86400000)
    public void deleteToken() {
//        List<AuthenticationResponse> expired = authenticationResponseRepository.findDateExpired(new Date());
//        List<Object> arr= Arrays.asList(expired);
//        for(AuthenticationResponse a:expired){
//            if (a == null) {
//                return;
//            } else {
//                authenticationResponseRepository.delete(a);
//            }
//        }
//        System.out.println("INSPECTION WALKING IN BACKGROUND\n");
    }
}
