package com.ecom.ecommerse.notification;

import com.ecom.ecommerse.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
