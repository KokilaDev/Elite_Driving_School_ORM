package lk.ijse.elite_driving_school_orm.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private String studentId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String nic;
    private String regDate;

    private List<String> courseIds;
}
