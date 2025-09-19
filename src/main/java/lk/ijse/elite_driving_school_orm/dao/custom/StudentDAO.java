package lk.ijse.elite_driving_school_orm.dao.custom;

import lk.ijse.elite_driving_school_orm.dao.CrudDAO;
import lk.ijse.elite_driving_school_orm.entity.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentDAO extends CrudDAO<Student> {
    List<Student> search(String text) throws SQLException;

    Optional<Student> findStudentByNic(String nic) throws SQLException;

    boolean existsStudentByPhoneNumber(String phoneNumber) throws SQLException;
}
