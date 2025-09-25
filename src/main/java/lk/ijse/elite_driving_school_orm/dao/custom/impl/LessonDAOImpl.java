package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.dao.custom.LessonDAO;
import lk.ijse.elite_driving_school_orm.entity.Lesson;
import lk.ijse.elite_driving_school_orm.entity.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LessonDAOImpl implements LessonDAO {
    @Override
    public List<Lesson> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Lesson lesson) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Lesson lesson) throws SQLException {
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
    public Optional<Lesson> findById(String id) throws SQLException {
        return Optional.empty();
    }
}
