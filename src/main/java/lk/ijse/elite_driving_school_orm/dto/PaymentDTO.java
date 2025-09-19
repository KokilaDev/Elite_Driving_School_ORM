package lk.ijse.elite_driving_school_orm.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lk.ijse.elite_driving_school_orm.entity.Student;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {
    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private String status;

    @ManyToOne
    private Student student;
}
