package lk.ijse.elite_driving_school_orm.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LessonTM {
    private String lessonId;
    private String description;
    private String date;
    private String time;
    private String courseId;
    private String instructorId;
}
