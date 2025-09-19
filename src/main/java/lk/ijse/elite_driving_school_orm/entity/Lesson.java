package lk.ijse.elite_driving_school_orm.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    private String lessonId;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Instructor instructor;
}
