package lk.ijse.elite_driving_school_orm;

import lk.ijse.elite_driving_school_orm.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_orm.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Lifecycle {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("John");

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(student);
        transaction.commit();

        session.close();

        student.setName("Doe");

        Session session1 = FactoryConfiguration.getInstance().getSession();

        session1.merge(student);

        Transaction transaction1 = session1.beginTransaction();

        session1.remove(student);

        transaction1.commit();

        Student student1 = session1.get(Student.class, 1);
    }
}
