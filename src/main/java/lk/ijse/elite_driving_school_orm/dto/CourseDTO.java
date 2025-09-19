package lk.ijse.elite_driving_school_orm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO {
    private String courseId;
    private String name;
    private int duration;
    private double fee;
}
