package lk.ijse.elite_driving_school_orm.bo.custom;

import lk.ijse.elite_driving_school_orm.bo.SuperBO;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;

import java.sql.SQLException;
import java.util.List;

public interface LessonBO extends SuperBO {
    List<LessonDTO> getAllLessons() throws SQLException;
}
