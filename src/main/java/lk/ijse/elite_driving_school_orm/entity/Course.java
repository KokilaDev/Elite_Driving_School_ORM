package lk.ijse.elite_driving_school_orm.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private int duration;

    @Column(name = "fee", length = 10)
    private double fee;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;
}
