module lk.ijse.elite_driving_school_orm {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens lk.ijse.elite_driving_school_orm to javafx.fxml;
    opens lk.ijse.elite_driving_school_orm.controller to javafx.fxml;
    opens lk.ijse.elite_driving_school_orm.util to javafx.fxml;
    opens lk.ijse.elite_driving_school_orm.entity to org.hibernate.orm.core;

    exports lk.ijse.elite_driving_school_orm;
    exports lk.ijse.elite_driving_school_orm.util;
}