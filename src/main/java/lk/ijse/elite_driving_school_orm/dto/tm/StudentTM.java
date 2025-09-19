package lk.ijse.elite_driving_school_orm.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentTM {
    private String studentId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String nic;
    private String regDate;
}
