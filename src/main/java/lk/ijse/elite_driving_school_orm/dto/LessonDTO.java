package lk.ijse.elite_driving_school_orm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LessonDTO {
    private String lessonId;
    private String date;
    private String time;

    private String studentId;
    private String courseId;
    private String instructorId;
}
