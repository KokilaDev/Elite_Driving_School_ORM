package lk.ijse.elite_driving_school_orm.bo.custom.impl;

import lk.ijse.elite_driving_school_orm.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;

import java.util.List;

public class LessonBOImpl implements LessonBO {
    @Override
    public List<LessonDTO> getAllLessons() {
        return List.of();
    }
}
