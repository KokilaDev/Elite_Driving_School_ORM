package lk.ijse.elite_driving_school_orm.bo.custom.impl;

import lk.ijse.elite_driving_school_orm.bo.custom.StudentBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.bo.exception.InUseException;
import lk.ijse.elite_driving_school_orm.bo.exception.NotFoundException;
import lk.ijse.elite_driving_school_orm.bo.util.EntityDTOConverter;
import lk.ijse.elite_driving_school_orm.dao.DAOFactory;
import lk.ijse.elite_driving_school_orm.dao.DAOTypes;
import lk.ijse.elite_driving_school_orm.dao.custom.CourseDAO;
import lk.ijse.elite_driving_school_orm.dao.custom.StudentDAO;
import lk.ijse.elite_driving_school_orm.dto.StudentDTO;
import lk.ijse.elite_driving_school_orm.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);
    private final CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<StudentDTO> getAllStudent() throws SQLException {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(converter.getStudentDTO(student));
        }
        return studentDTOs;
    }

    @Override
    public void saveStudent(StudentDTO studentDTO) throws DuplicateException, Exception {
        Optional<Student> optionalStudent = studentDAO.findById(studentDTO.getStudentId());
        if (optionalStudent.isPresent()) {
            throw new DuplicateException("Student already exists");
        }

        Optional<Student> optionalStudent1 = studentDAO.findStudentByNic(studentDTO.getNic());
        if (optionalStudent1.isPresent()) {
            throw new DuplicateException("Student already exists");
        }

        if (studentDAO.existsStudentByPhoneNumber(studentDTO.getPhone())) {
            throw new DuplicateException("Student already exists");
        }

        Student student = converter.getStudent(studentDTO);

        boolean save = studentDAO.save(student);
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) throws SQLException {
        Optional<Student> optionalStudent = studentDAO.findById(studentDTO.getStudentId());
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException("Student not found");
        }

        Optional<Student> optionalStudent1 = studentDAO.findStudentByNic(studentDTO.getNic());
        if (optionalStudent1.isPresent()) {
            Student student = optionalStudent1.get();

            if (student.getStudentId() != studentDTO.getStudentId()) {
                throw new DuplicateException("Student already exists");
            }
        }

        Student student = converter.getStudent(studentDTO);
        studentDAO.update(student);
    }

    @Override
    public boolean deleteStudent(String id) throws InUseException, Exception {
        Optional<Student> optionalStudent = studentDAO.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException("Student not found");
        }

        return false;
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }
}
