package lk.ijse.elite_driving_school_orm.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseTM {
    private String courseId;
    private String name;
    private int duration;
    private double fee;
}
