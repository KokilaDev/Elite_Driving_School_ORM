package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_orm.entity.Course;
import lk.ijse.elite_driving_school_orm.entity.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public List<Course> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Course course) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Course course) throws SQLException {
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
    public Optional<Course> findById(String id) throws SQLException {
        return Optional.empty();
    }
}
