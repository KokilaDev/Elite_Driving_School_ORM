package lk.ijse.elite_driving_school_orm.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class InstructorTM {

    private String instructorId;
    private String name;
    private String email;
    private String phone;
    private String specialization;
}
