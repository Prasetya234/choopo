package com.example.choopo.engineer.schedulingtasks;

import com.example.choopo.engineer.model.TemporaryToken;
import com.example.choopo.engineer.repository.TemporaryTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    @Autowired
    private TemporaryTokenRepository temporaryTokenRepository;

    //86400000 = 1 Day
    @Scheduled(fixedDelay = 86400000)
    public void deleteToken() {
        List<TemporaryToken> expired = temporaryTokenRepository.findDateExpired(new Date());
        List<Object> arr= Arrays.asList(expired);
        for(TemporaryToken a:expired){
            if (a == null) {
                return;
            } else {
                temporaryTokenRepository.delete(a);
            }
        }
        System.out.println("INSPECTION WALKING IN BACKGROUND\n");
    }
}
