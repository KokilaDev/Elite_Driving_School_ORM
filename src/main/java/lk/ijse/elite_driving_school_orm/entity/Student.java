package lk.ijse.elite_driving_school_orm.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "nic")
    private String nic;

    @Column(name = "regDate")
    private String regDate;
}
