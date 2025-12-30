package vn.tqd.mobilemall.notificationservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tqd.mobilemall.notificationservice.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    // Lấy danh sách thông báo của 1 user (có phân trang)

    Page<Notification> findByUserId(String userId, Pageable pageable);

    // Đếm số thông báo chưa đọc (nếu bạn thêm trạng thái READ/UNREAD)
    // Long countByUserIdAndStatus(String userId, NotificationStatus status);
}