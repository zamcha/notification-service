package vn.tqd.mobilemall.notificationservice.service;

import vn.tqd.mobilemall.notificationservice.dto.SendNotificationMallRequest;
import vn.tqd.mobilemall.notificationservice.dto.SendNotificationRequest;

public interface SendNotificationService {
    void sendNotification(SendNotificationRequest request);
    void sendNotificationMall(SendNotificationMallRequest request);
}
