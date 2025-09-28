package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_orm.dao.custom.LessonDAO;
import lk.ijse.elite_driving_school_orm.entity.Instructor;
import lk.ijse.elite_driving_school_orm.entity.Lesson;
import lk.ijse.elite_driving_school_orm.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LessonDAOImpl implements LessonDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Lesson> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Lesson> query = session.createQuery("FROM Lesson", Lesson.class);
            List<Lesson> lessonList = query.list();
            return lessonList;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT les.id FROM Lesson les ORDER BY les.id DESC",
                    String.class
            ).setMaxResults(1);
            List<String> lessonlist = query.list();
            if (lessonlist.isEmpty()) {
                return null;
            }
            return lessonlist.get(0);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean save(Lesson lesson) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(lesson);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
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
        Session session = factoryConfiguration.getSession();
        try {
            Lesson lesson = session.get(Lesson.class, id);
            return Optional.ofNullable(lesson);
        } finally {
            session.close();
        }
    }
}
