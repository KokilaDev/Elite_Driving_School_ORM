package lk.ijse.elite_driving_school_orm.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.elite_driving_school_orm.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.bo.exception.InUseException;
import lk.ijse.elite_driving_school_orm.bo.exception.NotFoundException;
import lk.ijse.elite_driving_school_orm.bo.util.EntityDTOConverter;
import lk.ijse.elite_driving_school_orm.dao.DAOFactory;
import lk.ijse.elite_driving_school_orm.dao.DAOTypes;
import lk.ijse.elite_driving_school_orm.dao.custom.InstructorDAO;
import lk.ijse.elite_driving_school_orm.dto.InstructorDTO;
import lk.ijse.elite_driving_school_orm.dto.StudentDTO;
import lk.ijse.elite_driving_school_orm.entity.Instructor;
import lk.ijse.elite_driving_school_orm.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstructorBOImpl implements InstructorBO {

    private final InstructorDAO instructorDAO = DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTOR);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<InstructorDTO> getAllInstructor() throws SQLException {

        List<Instructor> instructors = instructorDAO.getAll();
        List<InstructorDTO> instructorDTOS = new ArrayList<>();
        for (Instructor instructor : instructors) {
            instructorDTOS.add(converter.getInstructorDTO(instructor));
        }
        return instructorDTOS;

    }

    @Override
    public void saveInstructor(InstructorDTO instructorDTO) throws DuplicateException, Exception {
        Optional<Instructor> optionalInstructor = instructorDAO.findById(instructorDTO.getInstructorId());
        if (optionalInstructor.isPresent()) {
            throw new DuplicateException("Instructor already exists");
        }

        Instructor instructor = converter.getInstructor(instructorDTO);

        boolean save = instructorDAO.save(instructor);
    }

    @Override
    public String getNextId() throws SQLException {
        String lastId =  instructorDAO.getLastId();
        char tablechar = 'I';
        if (lastId != null){
            String lastNumberString = lastId.substring(1);
            int lastNumber = Integer.parseInt(lastNumberString);
            int nextId = lastNumber + 1;
            return String.format(tablechar +"%03d",nextId);
        }

        return tablechar + "001";
    }

    @Override
    public boolean updateInstructor(InstructorDTO instructorDTO) throws SQLException {
        Optional<Instructor> optionalInstructor = instructorDAO.findById(instructorDTO.getInstructorId());
        if (optionalInstructor.isEmpty()) {
            throw new NotFoundException("Instructor not found");
        }

        Instructor instructor = converter.getInstructor(instructorDTO);
        boolean isUpdate = instructorDAO.update(instructor);

        return  isUpdate;

    }

    @Override
    public boolean deleteInstructor(String id) throws InUseException, Exception {
        Optional<Instructor> optionalInstructor = instructorDAO.findById(id);
        if (optionalInstructor.isEmpty()) {
            throw new NotFoundException("Instructor not found");
        }

        try{

            boolean delete = instructorDAO.delete(id);

        }catch (Exception e){

            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Instructor delete not found").show();
        }
        return false;
    }
}
