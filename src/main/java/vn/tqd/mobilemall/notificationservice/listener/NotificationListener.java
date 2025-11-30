package vn.tqd.mobilemall.notificationservice.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import vn.tqd.mobilemall.notificationservice.dto.SendNotificationMallRequest;
import vn.tqd.mobilemall.notificationservice.dto.SendNotificationRequest;

@Component
@Slf4j
public class NotificationListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queue.notification.queue}", durable = "true"),
            exchange = @Exchange(value = "${queue.notification.exchange}", type = ExchangeTypes.DIRECT),
            key = "${queue.notification.routing-key}"
    ))
    public void receiveNotification(SendNotificationRequest request) {
        log.info("(receiveNotification)Received notification: {}", request);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queue.notification-mall.queue}", durable = "true"),
            exchange = @Exchange(value = "${queue.notification-mall.exchange}", type = ExchangeTypes.DIRECT),
            key = "${queue.notification-mall.routing-key}"
    ))
    public void receiveNotificationMall(SendNotificationMallRequest request) {
        log.info("(receiveNotification)Received notification: {}", request);
    }

}