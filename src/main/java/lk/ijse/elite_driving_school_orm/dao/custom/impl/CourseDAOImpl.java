package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_orm.dao.SQLUtil;
import lk.ijse.elite_driving_school_orm.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_orm.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDAOImpl implements CourseDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Course> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Course> query = session.createQuery("from Course", Course.class);
            List<Course> courseList = query.list();
            return courseList;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT c.id FROM Course c ORDER BY c.id DESC",
                    String.class
            ).setMaxResults(1);
            List<String> courseList = query.list();
            if (courseList.isEmpty()) {
                return null;
            }
            return courseList.get(0);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean save(Course course) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(course);
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
    public boolean update(Course course) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(course);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Course course = session.get(Course.class, id);
            if (course != null) {
                session.remove(course);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT course_id FROM courses");
        List<String> courseIdList = new ArrayList<>();
        while (resultSet.next()) {
            courseIdList.add(resultSet.getString(1));
        }
        return courseIdList;
    }

    @Override
    public Optional<Course> findById(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Course course = session.get(Course.class, id);
            return Optional.ofNullable(course);
        } finally {
            session.close();
        }
    }
}
