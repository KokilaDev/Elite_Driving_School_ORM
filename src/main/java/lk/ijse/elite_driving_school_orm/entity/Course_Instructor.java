package lk.ijse.elite_driving_school_orm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course_instructor")
public class Course_Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(name = "assigned_date")
    private String assignedDate;

    @Column(name = "role")
    private String role;

    @Column(name = "hours_per_week")
    private int hoursPerWeek;
}
