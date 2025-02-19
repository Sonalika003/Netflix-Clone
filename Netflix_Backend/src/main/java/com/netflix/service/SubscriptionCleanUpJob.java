package com.netflix.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.netflix.entity.Subscription;
import com.netflix.entity.User_Subscription;
import com.netflix.repository.User_subscription_Repository;


/**
 * 
 * @author Aman_Maurya
 *
 *This class is used to Delete OTP from record from after 10 minute of registered time
 */
@Component
public class SubscriptionCleanUpJob {

	
    @Autowired
    private User_subscription_Repository subRepository;

    @Scheduled(fixedDelay = 1 * 60 * 1000) // run after every 10 minutes
    public void deleteExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        List<User_Subscription> expiredTokens = subRepository.findByCreatedAtBefore(now.minusMinutes(28*24*60));
        // Reset all expired subscription tokens from database to NONE
        for(int i=0; i<expiredTokens.size();i++) {
        	expiredTokens.get(i).setSubscription(Subscription.NONE);
        }
        subRepository.saveAll(expiredTokens);
    }
}

