//package vn.tqd.mobilemall.notificationservice.listener;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.ExchangeTypes;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//import vn.tqd.mobilemall.notificationservice.dto.SendNotificationMallRequest;
//import vn.tqd.mobilemall.notificationservice.dto.SendNotificationRequest;
//
//@Component
//@Slf4j
//public class NotificationListener {
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "${queue.notification.queue}", durable = "true"),
//            exchange = @Exchange(value = "${queue.notification.exchange}", type = ExchangeTypes.DIRECT),
//            key = "${queue.notification.routing-key}"
//    ))
//    public void receiveNotification(SendNotificationRequest request) {
//        log.info("(receiveNotification)Received notification: {}", request);
//    }
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "${queue.notification-mall.queue}", durable = "true"),
//            exchange = @Exchange(value = "${queue.notification-mall.exchange}", type = ExchangeTypes.DIRECT),
//            key = "${queue.notification-mall.routing-key}"
//    ))
//    public void receiveNotificationMall(SendNotificationMallRequest request) {
//        log.info("(receiveNotification)Received notification: {}", request);
//    }
//
//}
package vn.tqd.mobilemall.notificationservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.tqd.mobilemall.notificationservice.dto.NotificationRequest; // DTO bên Notification
import vn.tqd.mobilemall.notificationservice.service.SendNotificationService;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final SendNotificationService notificationService;


    // Lắng nghe hàng đợi
    @RabbitListener(queues = "${queue.notification-mall.queue}") // Điền trực tiếp tên hoặc lấy từ Config
    public void receiveMessage(NotificationRequest request) {
        log.info("Đã nhận tin nhắn từ RabbitMQ: {}", request);

        // Gọi Service để xử lý (Lưu DB, Gửi Gmail...)
        try {
            // Mapping từ Payload sang Request nội bộ của Service nếu cần
            // Ở đây giả sử service nhận luôn object này
            notificationService.sendNotification(request);

        } catch (Exception e) {
            log.error("Lỗi khi gửi email: {}", e.getMessage(), e);
            // RabbitMQ có cơ chế Retry, nếu ném lỗi ra nó sẽ thử lại hoặc đẩy vào Dead Letter Queue
        }
    }
    @RabbitListener(queues = "${queue.notification.queue}")
    public void receiveNotification(NotificationRequest request) {
        log.info("Nhận yêu cầu gửi notification cho email: {}", request.getEmail());

        try {
            // Check loại notification
            if ("EMAIL".equalsIgnoreCase(request.getType())) {
                notificationService.sendNotification(request);
                log.info("Gửi email thành công tới {}", request.getEmail());
            }
            // Có thể mở rộng thêm case "PUSH", "SMS"...

        } catch (Exception e) {
            log.error("Lỗi khi xử lý notification: {}", e.getMessage());
            // Có thể bắn vào Dead Letter Queue (DLQ) nếu cần retry
        }
    }
}