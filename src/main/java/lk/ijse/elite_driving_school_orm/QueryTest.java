package lk.ijse.elite_driving_school_orm;

import lk.ijse.elite_driving_school_orm.config.FactoryConfiguration;
import lk.ijse.elite_driving_school_orm.entity.Course;
import lk.ijse.elite_driving_school_orm.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryTest {
    public static void main(String[] args) {
        FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

        Session session = factoryConfiguration.getSession();

        Query<Object[]> query = session.createQuery(
                "",
                Object[].class
        );

        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            Student student = (Student) objects[0];
            Course course = (Course) objects[1];
            System.out.println(student.toString());
            System.out.println(course.toString());
        }
    }
}
