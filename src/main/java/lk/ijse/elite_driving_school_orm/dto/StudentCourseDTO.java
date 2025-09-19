package lk.ijse.elite_driving_school_orm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentCourseDTO {
    private String studentId;
    private String courseId;
    private String enrollDate;
    private String status;
    private String grade;
}
