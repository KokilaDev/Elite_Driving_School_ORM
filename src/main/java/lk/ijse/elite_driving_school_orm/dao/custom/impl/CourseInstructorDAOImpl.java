package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.dao.custom.CourseInstructorDAO;
import lk.ijse.elite_driving_school_orm.entity.Course_Instructor;
import lk.ijse.elite_driving_school_orm.entity.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseInstructorDAOImpl implements CourseInstructorDAO {
    @Override
    public List<Course_Instructor> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Course_Instructor courseInstructor) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Course_Instructor courseInstructor) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<Course_Instructor> findById(String id) throws SQLException {
        return Optional.empty();
    }
}
