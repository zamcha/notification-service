-- ==========================================
-- MODULE NOTIFICATION (Thông báo)
-- ==========================================

-- 3. Bảng notifications
CREATE TABLE IF NOT EXISTS notifications (
                                             notification_id CHAR(36) NOT NULL,      -- UUID
    user_id CHAR(36) NOT NULL,              -- Gửi cho ai
    type VARCHAR(50) NOT NULL,              -- ORDER_SUCCESS, PROMOTION...
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,                  -- Nội dung dài
    channel VARCHAR(20) DEFAULT 'IN_APP',   -- Kênh gửi
    status VARCHAR(20) DEFAULT 'SENT',      -- Trạng thái
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (notification_id),
    KEY idx_noti_user (user_id)             -- Index để query nhanh theo user
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;