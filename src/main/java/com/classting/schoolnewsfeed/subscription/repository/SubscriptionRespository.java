package com.classting.schoolnewsfeed.subscription.repository;

import com.classting.schoolnewsfeed.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRespository extends JpaRepository<Subscription, Long> {
}
