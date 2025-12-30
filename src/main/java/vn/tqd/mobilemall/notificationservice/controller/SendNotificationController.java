package vn.tqd.mobilemall.notificationservice.controller;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tqd.mobilemall.notificationservice.dto.NotificationRequest;
import vn.tqd.mobilemall.notificationservice.dto.SendNotificationMallRequest;
import vn.tqd.mobilemall.notificationservice.dto.SendNotificationRequest;
import vn.tqd.mobilemall.notificationservice.service.SendNotificationService;

@RequestMapping("/api/v1/notifications")
@RestController
@RequiredArgsConstructor
public class SendNotificationController {
    private final SendNotificationService sendNotificationService;

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody NotificationRequest request) {
        sendNotificationService.sendNotification(request);
        return ResponseEntity.noContent().build();
    }
//    @PostMapping(value = "/mall")
//    public ResponseEntity<Void> sendNotificationMall(@RequestBody SendNotificationMallRequest request) {
//        sendNotificationService.sendNotificationMall(request);
//        return ResponseEntity.noContent().build();
//    }
}
