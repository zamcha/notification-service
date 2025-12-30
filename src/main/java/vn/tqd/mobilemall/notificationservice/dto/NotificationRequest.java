package vn.tqd.mobilemall.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest implements Serializable {
    private String userId;      // ID người nhận
    private String email;       // Email người nhận
    private String title;       // Tiêu đề
    private String content;     // Nội dung
    private String type;        // Loại: ORDER_SUCCESS, PROMOTION...
}