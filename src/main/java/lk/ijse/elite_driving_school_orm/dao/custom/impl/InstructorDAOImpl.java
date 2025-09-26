package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_orm.dao.custom.InstructorDAO;
import lk.ijse.elite_driving_school_orm.entity.Instructor;
import lk.ijse.elite_driving_school_orm.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InstructorDAOImpl implements InstructorDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public List<Instructor> getAll() throws SQLException {

        Session session = factoryConfiguration.getSession();
        try {
            Query<Instructor> query = session.createQuery("FROM Instructor", Instructor.class);
            List<Instructor> instructorList = query.list();
            return instructorList;
        } finally {
            session.close();
        }

    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Instructor instructor) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Instructor instructor) throws SQLException {
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
    public Optional<Instructor> findById(String id) throws SQLException {
        return Optional.empty();
    }
}
