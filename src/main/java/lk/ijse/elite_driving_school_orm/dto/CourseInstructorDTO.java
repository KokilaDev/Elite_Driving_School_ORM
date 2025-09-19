package lk.ijse.elite_driving_school_orm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseInstructorDTO {
    private String courseId;
    private String instructorId;
    private String assignedDate;
    private String role;
    private int hoursPerWeek;
}
