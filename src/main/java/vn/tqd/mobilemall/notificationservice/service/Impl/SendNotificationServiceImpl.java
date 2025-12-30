package vn.tqd.mobilemall.notificationservice.service.Impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.tqd.mobilemall.notificationservice.dto.NotificationRequest;
import vn.tqd.mobilemall.notificationservice.entity.Notification;
import vn.tqd.mobilemall.notificationservice.entity.enums.NotificationChannel;
import vn.tqd.mobilemall.notificationservice.entity.enums.NotificationStatus;
import vn.tqd.mobilemall.notificationservice.entity.enums.NotificationType;
import vn.tqd.mobilemall.notificationservice.repository.NotificationRepository;
import vn.tqd.mobilemall.notificationservice.service.SendNotificationService;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendNotificationServiceImpl   implements SendNotificationService {

    // Inject Bean có sẵn của Spring Boot
    private final JavaMailSender javaMailSender;
    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationRequest request) {
        // BƯỚC 1: Lưu thông báo vào DB trước (Trạng thái PENDING)
        // Việc này đảm bảo dù gửi mail lỗi thì vẫn có log trong hệ thống
        Notification notification = Notification.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .content(request.getContent())
                .type(NotificationType.valueOf(request.getType())) // Convert String -> Enum
                .channel(NotificationChannel.EMAIL) // Hoặc lấy từ request
                .status(NotificationStatus.PENDING) // Mặc định là Đang chờ
                .build();

        // Lưu lần 1: Để có ID
        notification = notificationRepository.save(notification);
        log.info("Bắt đầu gửi email đến: {}", request.getEmail());

        // Kiểm tra nếu request yêu cầu gửi qua EMAIL
        // (Hoặc mặc định luôn gửi Email tùy logic của bạn)
        try {
            sendHtmlEmail(request.getEmail(), request.getTitle(), request.getContent());
            notification.setStatus(NotificationStatus.SENT);
            log.info("Email đã được gửi thành công!");
        } catch (MessagingException e) {
            log.error("Lỗi khi gửi email: ", e);
            // Nếu lỗi -> Cập nhật trạng thái FAILED
            notification.setStatus(NotificationStatus.FAILED);
        }
        notificationRepository.save(notification);
    }

    // Hàm gửi Email hỗ trợ HTML (In đậm, link...)
    private void sendHtmlEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        // Multipart = true để hỗ trợ HTML + đính kèm
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true); // true = nội dung là HTML

        // Gmail chỉ cho phép gửi từ chính username bạn cấu hình trong spring.mail.username
        // Nếu muốn hiển thị tên "Mobile Mall", dùng overload có thêm personal name
        helper.setFrom("caotran293@gmail.com");

        javaMailSender.send(message);
    }

}