package lk.ijse.elite_driving_school_orm.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentTM {
    private String paymentId;
    private String amount;
    private String date;
    private String status;
    private String studentId;
}
