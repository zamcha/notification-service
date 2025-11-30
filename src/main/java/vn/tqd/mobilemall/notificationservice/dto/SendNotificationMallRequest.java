package vn.tqd.mobilemall.notificationservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendNotificationMallRequest{
    private Integer orderId;
    private Integer userId;
    private Double amount;
    private String message;
};
