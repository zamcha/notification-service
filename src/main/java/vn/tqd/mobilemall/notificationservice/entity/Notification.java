package vn.tqd.mobilemall.notificationservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.tqd.mobilemall.notificationservice.entity.enums.NotificationChannel;
import vn.tqd.mobilemall.notificationservice.entity.enums.NotificationStatus;
import vn.tqd.mobilemall.notificationservice.entity.enums.NotificationType;


import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    // Bảng này không nhất thiết phải kế thừa BaseEntity nếu bạn muốn tối giản
    // Nhưng để nhất quán ID UUID, ta tự định nghĩa ID

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "notification_id")
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType type;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel")
    @Builder.Default
    private NotificationChannel channel = NotificationChannel.IN_APP;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private NotificationStatus status = NotificationStatus.SENT;

    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}