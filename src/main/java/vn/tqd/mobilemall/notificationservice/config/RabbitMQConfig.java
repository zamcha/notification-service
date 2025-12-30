package vn.tqd.mobilemall.notificationservice.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Tên Queue phải KHỚP 100% với bên Mall
    @Value("${queue.notification-mall.queue}")
    private String queueName;
    @Value("${queue.notification.queue}")
    private String queueName1;

    // Converter: Chuyển JSON -> Object
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}