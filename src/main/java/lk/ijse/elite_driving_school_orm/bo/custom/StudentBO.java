package lk.ijse.elite_driving_school_orm.bo.custom;

import lk.ijse.elite_driving_school_orm.bo.SuperBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.bo.exception.InUseException;
import lk.ijse.elite_driving_school_orm.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAllStudent() throws SQLException;

    void saveStudent(StudentDTO studentDTO) throws DuplicateException, Exception;

    boolean updateStudent(StudentDTO studentDTO) throws SQLException;

    boolean deleteStudent(String id) throws InUseException, Exception;

    String getNextId() throws SQLException;
}
