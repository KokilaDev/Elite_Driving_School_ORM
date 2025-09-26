package lk.ijse.elite_driving_school_orm.bo.custom;

import lk.ijse.elite_driving_school_orm.bo.SuperBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.bo.exception.InUseException;
import lk.ijse.elite_driving_school_orm.dto.InstructorDTO;
import lk.ijse.elite_driving_school_orm.dto.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface InstructorBO extends SuperBO {

    List<InstructorDTO> getAllInstructor() throws SQLException;

    void saveInstructor(InstructorDTO instructorDTO) throws DuplicateException, Exception;

    String getNextId() throws SQLException;

    boolean updateInstructor(InstructorDTO instructorDTO) throws SQLException;

    boolean deleteInstructor(String id) throws InUseException, Exception;

}
