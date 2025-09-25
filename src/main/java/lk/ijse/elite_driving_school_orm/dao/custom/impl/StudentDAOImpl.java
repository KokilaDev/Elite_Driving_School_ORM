package lk.ijse.elite_driving_school_orm.dao.custom.impl;

import lk.ijse.elite_driving_school_orm.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_orm.dao.SQLUtil;
import lk.ijse.elite_driving_school_orm.dao.custom.StudentDAO;
import lk.ijse.elite_driving_school_orm.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class StudentDAOImpl implements StudentDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Student> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            List<Student> studentList = query.list();
            return studentList;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT stu.id FROM Student stu ORDER BY stu.id DESC",
                    String.class
            ).setMaxResults(1);
            List<String> studentList = query.list();
            if (studentList.isEmpty()) {
                return null;
            }
            return studentList.get(0);
        } finally {
            session.close();
        }
    }

    @Override
    public boolean save(Student student) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(student);
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
    public boolean update(Student student) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(student);
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
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
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
        ResultSet resultSet = SQLUtil.execute("SELECT student_id FROM students");
        List<String> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }

    @Override
    public Optional<Student> findById(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            Student student = session.get(Student.class, id);
            return Optional.ofNullable(student);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> search(String text) throws SQLException {
        String searchText = "%" + text + "%";
        Session session = factoryConfiguration.getSession();
        try {
            Query<Student> query = session.createQuery(
                    "FROM Student stu WHERE stu.id LIKE :text " +
                            "OR stu.name LIKE :text " +
                            "OR stu.nic LIKE :text " +
                            "OR stu.email LIKE :text " +
                            "OR stu.phone LIKE :text",
                    Student.class
            );
            query.setParameter("text", searchText);
            List<Student> studentList = query.list();
            return studentList;
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Student> findStudentByNic(String nic) throws SQLException {
//        ResultSet resultSet = SQLUtil.execute("SELECT * FROM student WHERE nic = ?", nic);
//        if (resultSet.next()) {
//            return Optional.of(new Student(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6),
//                    resultSet.getString(7)
//            ));
//        }
        return Optional.empty();
    }

    @Override
    public boolean existsStudentByPhoneNumber(String phoneNumber) throws SQLException {
        ResultSet resultSet = SQLUtil.execute(
                "SELECT 1 FROM students WHERE phone = ?", phoneNumber
        );
        return resultSet.next();
    }
}
