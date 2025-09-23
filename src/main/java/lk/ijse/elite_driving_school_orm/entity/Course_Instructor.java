package lk.ijse.elite_driving_school_orm.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "course_instructor")
public class Course_Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @Column(name = "assigned_date")
    private String assignedDate;

    @Column(name = "role")
    private String role;

    @Column(name = "hours_per_week")
    private int hoursPerWeek;
}
