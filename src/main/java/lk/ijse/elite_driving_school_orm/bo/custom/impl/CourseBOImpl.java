package lk.ijse.elite_driving_school_orm.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.elite_driving_school_orm.bo.custom.CourseBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.bo.exception.InUseException;
import lk.ijse.elite_driving_school_orm.bo.exception.NotFoundException;
import lk.ijse.elite_driving_school_orm.bo.util.EntityDTOConverter;
import lk.ijse.elite_driving_school_orm.dao.DAOFactory;
import lk.ijse.elite_driving_school_orm.dao.DAOTypes;
import lk.ijse.elite_driving_school_orm.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_orm.dto.CourseDTO;
import lk.ijse.elite_driving_school_orm.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseBOImpl implements CourseBO {
    private final CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<CourseDTO> getAllCourses() throws SQLException {
        List<Course> courses = courseDAO.getAll();
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course : courses) {
            courseDTOs.add(converter.getCourseDTO(course));
        }
        return courseDTOs;
    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws DuplicateException, Exception {
        Optional<Course> optionalCourse = courseDAO.findById(courseDTO.getCourseId());
        if (optionalCourse.isPresent()) {
            throw new DuplicateException("Course already exists");
        }

        Course course = converter.getCourse(courseDTO);

        boolean save = courseDAO.save(course);
        return save;
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws SQLException {
        Optional<Course> optionalCourse = courseDAO.findById(courseDTO.getCourseId());
        if (optionalCourse.isEmpty()) {
            throw new InUseException("Course does not exist");
        }

        Course course = converter.getCourse(courseDTO);
        courseDAO.update(course);
        return false;
    }

    @Override
    public boolean deleteCourse(String id) throws InUseException, Exception {
        Optional<Course> optionalCourse = courseDAO.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new NotFoundException("Course does not exist");
        }

        try {
            boolean delete = courseDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Course delete not found").show();
        }
        return false;
    }

    @Override
    public String getNextId() throws SQLException {
        String lastId = courseDAO.getLastId();
        char tablechar = 'C';
        if (lastId != null) {
            String lastNumberString = lastId.substring(1);
            int lastNumber = Integer.parseInt(lastNumberString);
            int nextId = lastNumber + 1;
            return String.format(tablechar + "%03d", nextId);
        }
        return tablechar + "001";
    }
}
