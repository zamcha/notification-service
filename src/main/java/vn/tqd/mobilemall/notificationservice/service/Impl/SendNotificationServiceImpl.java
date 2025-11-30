package vn.tqd.mobilemall.notificationservice.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.tqd.mobilemall.notificationservice.dto.SendNotificationMallRequest;
import vn.tqd.mobilemall.notificationservice.dto.SendNotificationRequest;
import vn.tqd.mobilemall.notificationservice.service.SendNotificationService;

@Service
@Slf4j
public class SendNotificationServiceImpl implements SendNotificationService {
    @Override
    public void sendNotification(SendNotificationRequest request) {
        log.info("{sendNotification1}Send notification to {}", request);
    }

    @Override
    public void sendNotificationMall(SendNotificationMallRequest request) {
        log.info("{sendNotification1}Send notification to {}", request);
    }
}
