package lk.ijse.elite_driving_school_orm.bo.custom.impl;

import lk.ijse.elite_driving_school_orm.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.bo.util.EntityDTOConverter;
import lk.ijse.elite_driving_school_orm.dao.DAOFactory;
import lk.ijse.elite_driving_school_orm.dao.DAOTypes;
import lk.ijse.elite_driving_school_orm.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_orm.dao.custom.InstructorDAO;
import lk.ijse.elite_driving_school_orm.dao.custom.LessonDAO;
import lk.ijse.elite_driving_school_orm.dao.custom.impl.CourseDAOImpl;
import lk.ijse.elite_driving_school_orm.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;
import lk.ijse.elite_driving_school_orm.entity.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonBOImpl implements LessonBO {
    private LessonDAO lessonDAO = DAOFactory.getInstance().getDAO(DAOTypes.LESSON);
    // Assuming you have DAOs initialized
    CourseDAO courseDAO = new CourseDAOImpl();
    InstructorDAO instructorDAO = new InstructorDAOImpl();

    // Pass them to the converter
    EntityDTOConverter converter = new EntityDTOConverter(courseDAO, instructorDAO);

    @Override
    public List<LessonDTO> getAllLessons() throws SQLException {
        List<Lesson> lessons = lessonDAO.getAll();
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonDTOS.add(converter.getLessonDTO(lesson));
        }
        return lessonDTOS;
    }

    @Override
    public void saveLesson(LessonDTO lessonDTO) throws DuplicateException, Exception {
        Optional<Lesson> optionalStudent = lessonDAO.findById(lessonDTO.getLessonId());
        if (optionalStudent.isPresent()) {
            throw new DuplicateException("Student already exists");
        }

        Lesson lesson = converter.getLesson(lessonDTO);

        boolean save = lessonDAO.save(lesson);
    }

    @Override
    public String getNextId() throws SQLException {
        String lastId =  lessonDAO.getLastId();
        char tablechar = 'L';
        if (lastId != null){
            String lastNumberString = lastId.substring(1);
            int lastNumber = Integer.parseInt(lastNumberString);
            int nextId = lastNumber + 1;
            return String.format(tablechar +"%03d",nextId);
        }
        return tablechar + "001";
    }
}
