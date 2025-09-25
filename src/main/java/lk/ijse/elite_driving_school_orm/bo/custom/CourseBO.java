package lk.ijse.elite_driving_school_orm.bo.custom;

import lk.ijse.elite_driving_school_orm.bo.SuperBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.bo.exception.InUseException;
import lk.ijse.elite_driving_school_orm.dto.CourseDTO;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;

import java.sql.SQLException;
import java.util.List;

public interface CourseBO extends SuperBO {
    List<CourseDTO> getAllCourses() throws SQLException;

    boolean saveCourse(CourseDTO courseDTO) throws DuplicateException, Exception;

    boolean updateCourse(CourseDTO courseDTO) throws SQLException;

    boolean deleteCourse(String id) throws InUseException, Exception;

    String getNextId() throws SQLException;

    List<LessonDTO> getAllLessons();

    List<LessonDTO> getLessonsByCourseId(String courseId);

    Object getLessonsById();
}
