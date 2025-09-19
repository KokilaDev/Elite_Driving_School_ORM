package lk.ijse.elite_driving_school_orm.dao;

import lk.ijse.elite_driving_school_orm.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory == null ?
                (daoFactory = new DAOFactory()) :
                daoFactory;
    }

    @SuppressWarnings("unchecked")
    public <T> T getDAO(DAOTypes daoTypes) {
        return switch (daoTypes) {
            case STUDENT -> (T) new StudentDAOImpl();
            case COURSE -> (T) new CourseDAOImpl();
            case INSTRUCTOR -> (T) new InstructorDAOImpl();
            case LESSON -> (T) new LessonDAOImpl();
            case PAYMENT -> (T) new PaymentDAOImpl();
            case STUDENT_COURSE -> (T) new StudentCourseDAOImpl();
            case COURSE_INSTRUCTOR -> (T) new CourseInstructorDAOImpl();
        };
    }
}
