package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.dao.custom.StudentCourseDAO;
import lk.ijse.elite_driving_school_orm.entity.Student;
import lk.ijse.elite_driving_school_orm.entity.Student_Course;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentCourseDAOImpl implements StudentCourseDAO {
    @Override
    public List<Student_Course> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Student_Course studentCourse) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Student_Course studentCourse) throws SQLException {
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
    public Optional<Student_Course> findById(String id) throws SQLException {
        return Optional.empty();
    }
}
