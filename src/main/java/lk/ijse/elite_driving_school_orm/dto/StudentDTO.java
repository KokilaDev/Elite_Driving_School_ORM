package lk.ijse.elite_driving_school_orm.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private String studentId;
    private String name;
    private String address;
    private String nic;
    private String email;
    private String phone;
    private LocalDate regDate;
}
