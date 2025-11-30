package vn.tqd.mobilemall.notificationservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendNotificationRequest {
    private String sendTo;
    private String title;
    private String content;

}
