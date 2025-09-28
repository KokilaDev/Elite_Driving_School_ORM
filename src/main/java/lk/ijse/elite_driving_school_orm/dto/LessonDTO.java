package lk.ijse.elite_driving_school_orm.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LessonDTO {
    private String lessonId;
    private String description;
    private LocalDate date;
    private String time;
    private String instructorId;
}
