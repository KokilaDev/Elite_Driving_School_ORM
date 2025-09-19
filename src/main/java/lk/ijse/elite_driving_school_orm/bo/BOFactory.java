package lk.ijse.elite_driving_school_orm.bo;

import lk.ijse.elite_driving_school_orm.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    @SuppressWarnings("unchecked")
    public <Hello extends SuperBO> Hello getBO(BOTypes boTypes) {
        return switch (boTypes) {
            case STUDENT -> (Hello) new StudentBOImpl();
            case COURSE -> (Hello) new CourseBOImpl();
            case INSTRUCTOR -> (Hello) new InstructorBOImpl();
            case LESSON -> (Hello) new LessonBOImpl();
            case PAYMENT -> (Hello) new PaymentBOImpl();
        };
    }
}
