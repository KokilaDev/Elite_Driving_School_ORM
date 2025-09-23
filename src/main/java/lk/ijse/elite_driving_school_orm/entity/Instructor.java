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
@Table(name = "instructors")
public class Instructor {
    @Id
    @Column(name = "instructor_id")
    private String instructorId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "instructor")
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "instructor")
    private List<Course_Instructor> courseInstructors;
}
