package lk.ijse.elite_driving_school_orm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payment {
    private String paymentId;
    private double amount;
    private String date;
    private String status;

    private String studentId;
}
