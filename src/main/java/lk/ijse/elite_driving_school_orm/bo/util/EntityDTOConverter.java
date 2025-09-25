package lk.ijse.elite_driving_school_orm.bo.util;

import lk.ijse.elite_driving_school_orm.dto.StudentDTO;
import lk.ijse.elite_driving_school_orm.entity.Student;

public class EntityDTOConverter {
    public StudentDTO getStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhone(student.getPhone());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setNic(student.getNic());
        studentDTO.setRegDate(student.getRegDate());
        return studentDTO;
    }

    public Student getStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());
        student.setAddress(studentDTO.getAddress());
        student.setNic(studentDTO.getNic());
        student.setRegDate(studentDTO.getRegDate());
        return student;
    }


}
