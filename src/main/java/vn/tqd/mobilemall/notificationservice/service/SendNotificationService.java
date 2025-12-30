package vn.tqd.mobilemall.notificationservice.service;

import jakarta.mail.MessagingException;
import vn.tqd.mobilemall.notificationservice.dto.NotificationRequest;
import vn.tqd.mobilemall.notificationservice.dto.SendNotificationMallRequest;
import vn.tqd.mobilemall.notificationservice.dto.SendNotificationRequest;

public interface SendNotificationService {
    void sendNotification(NotificationRequest request);
}
