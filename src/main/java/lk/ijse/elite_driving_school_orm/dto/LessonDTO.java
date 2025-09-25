package lk.ijse.elite_driving_school_orm.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LessonDTO {
    private String lessonId;
    private String description;
    private String date;
    private String time;

//    private String studentId;
    private String courseId;
    private String instructorId;

}
