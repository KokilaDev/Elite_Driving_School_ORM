package lk.ijse.elite_driving_school_orm.bo.util;

import lk.ijse.elite_driving_school_orm.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_orm.dao.custom.InstructorDAO;
import lk.ijse.elite_driving_school_orm.dto.*;
import lk.ijse.elite_driving_school_orm.entity.*;

import java.sql.SQLException;
import java.util.Optional;

public class EntityDTOConverter {

    private CourseDAO courseDAO;
    private InstructorDAO instructorDAO;

    public EntityDTOConverter() {}
    public EntityDTOConverter(CourseDAO courseDAO, InstructorDAO instructorDAO) {
        this.courseDAO = courseDAO;
        this.instructorDAO = instructorDAO;
    }

    // ------------------ Student ------------------
    public StudentDTO getStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhone(student.getPhone());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setNic(student.getNic());
        studentDTO.setRegDate(student.getRegDate());
        return studentDTO;
    }

    public Student getStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());
        student.setAddress(studentDTO.getAddress());
        student.setNic(studentDTO.getNic());
        student.setRegDate(studentDTO.getRegDate());
        return student;
    }

    // ------------------ Course ------------------
    public CourseDTO getCourseDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setName(course.getName());
        courseDTO.setDuration(course.getDuration());
        courseDTO.setFee(course.getFee());
        return courseDTO;
    }

    public Course getCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseId(courseDTO.getCourseId());
        course.setName(courseDTO.getName());
        course.setDuration(courseDTO.getDuration());
        course.setFee(courseDTO.getFee());
        return course;
    }

    // ------------------ Instructor ------------------
    public InstructorDTO getInstructorDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setInstructorId(instructor.getInstructorId());
        instructorDTO.setName(instructor.getName());
        instructorDTO.setEmail(instructor.getEmail());
        instructorDTO.setPhone(instructor.getPhone());
        instructorDTO.setSpecialization(instructor.getSpecialization());
        return instructorDTO;
    }

    public Instructor getInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(instructorDTO.getInstructorId());
        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setPhone(instructorDTO.getPhone());
        instructor.setSpecialization(instructorDTO.getSpecialization());
        return instructor;
    }

    // ------------------ Lesson ------------------
    public LessonDTO getLessonDTO(Lesson lesson) {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonId(lesson.getLessonId());
        lessonDTO.setDescription(lesson.getDescription());
        lessonDTO.setDate(lesson.getDate());
        lessonDTO.setTime(lesson.getTime());

        if (lesson.getInstructor() != null) {
            lessonDTO.setInstructorId(lesson.getInstructor().getInstructorId());
        }

        return lessonDTO;
    }

    public Lesson getLesson(LessonDTO lessonDTO) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonDTO.getLessonId());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setDate(lessonDTO.getDate());
        lesson.setTime(lessonDTO.getTime());

        if (lessonDTO.getInstructorId() != null) {
            Optional<Instructor> instructor = instructorDAO.findById(lessonDTO.getInstructorId());
            instructor.ifPresent(lesson::setInstructor);
        }

        return lesson;
    }

    // ------------------ Payment ------------------
    public PaymentDTO getPaymentDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setDate(payment.getDate());
        paymentDTO.setStatus(payment.getStatus());
        return paymentDTO;
    }

    public Payment getPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentDTO.getPaymentId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setDate(paymentDTO.getDate());
        payment.setStatus(paymentDTO.getStatus());
        return payment;
    }

    // ------------------ StudentCourse ------------------
    public StudentCourseDTO getStudentCourseDTO(Student_Course studentCourse) {
        StudentCourseDTO studentCourseDTO = new StudentCourseDTO();
        studentCourseDTO.setStudentId(studentCourse.getStudent().getStudentId());
        studentCourseDTO.setCourseId(studentCourse.getCourse().getCourseId());
        studentCourseDTO.setEnrollDate(studentCourse.getEnrollDate());
        return studentCourseDTO;
    }

    public Student_Course getStudentCourse(StudentCourseDTO studentCourseDTO) throws SQLException {
        Student_Course studentCourse = new Student_Course();

        if (studentCourseDTO.getStudentId() != null) {
            Student student = new Student();
            student.setStudentId(studentCourseDTO.getStudentId());
            studentCourse.setStudent(student);
        }

        if (studentCourseDTO.getCourseId() != null) {
            Optional<Course> course = courseDAO.findById(studentCourseDTO.getCourseId());
            course.ifPresent(studentCourse::setCourse);
        }

        studentCourse.setEnrollDate(studentCourseDTO.getEnrollDate());
        return studentCourse;
    }

    // ------------------ CourseInstructor ------------------
    public CourseInstructorDTO getCourseInstructorDTO(Course_Instructor courseInstructor) {
        CourseInstructorDTO courseInstructorDTO = new CourseInstructorDTO();
        courseInstructorDTO.setCourseId(courseInstructor.getCourse().getCourseId());
        courseInstructorDTO.setInstructorId(courseInstructor.getInstructor().getInstructorId());
        return courseInstructorDTO;
    }

    public Course_Instructor getCourseInstructor(CourseInstructorDTO courseInstructorDTO) throws SQLException {
        Course_Instructor courseInstructor = new Course_Instructor();

        if (courseInstructorDTO.getCourseId() != null) {
            Optional<Course> course = courseDAO.findById(courseInstructorDTO.getCourseId());
            course.ifPresent(courseInstructor::setCourse);
        }

        if (courseInstructorDTO.getInstructorId() != null) {
            Optional<Instructor> instructor = instructorDAO.findById(courseInstructorDTO.getInstructorId());
            instructor.ifPresent(courseInstructor::setInstructor);
        }

        return courseInstructor;
    }
}
