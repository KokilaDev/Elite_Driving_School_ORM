package lk.ijse.elite_driving_school_orm.bo.custom;

import lk.ijse.elite_driving_school_orm.bo.SuperBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;
import lk.ijse.elite_driving_school_orm.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface LessonBO extends SuperBO {
    List<LessonDTO> getAllLessons() throws SQLException;

    void saveLesson(LessonDTO lessonDTO) throws DuplicateException, Exception;

    String getNextId() throws SQLException;

}
