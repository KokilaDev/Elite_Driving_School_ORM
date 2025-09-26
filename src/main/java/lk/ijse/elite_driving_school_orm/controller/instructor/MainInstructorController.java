package lk.ijse.elite_driving_school_orm.controller.instructor;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_orm.dto.tm.InstructorTM;
import lk.ijse.elite_driving_school_orm.dto.tm.StudentTM;
import lk.ijse.elite_driving_school_orm.entity.Instructor;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainInstructorController implements Initializable {
    public AnchorPane ancInstructor;
    public Button btnAddNewInstructor;
    public TableView tblInstructor;
    public TableColumn colInstructorID;
    public TableColumn colName;
    public TableColumn colSpecialization;
    public TableColumn colEmail;
    public TableColumn colContact;
    public Button btnUpdate;
    public Button btnDelete;

    private final InstructorBO instructorBO = BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        try{
            loadTableData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnAddNewInstructor(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancInstructor, "/view/instructor/AddInstructor.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancInstructor, "/view/instructor/UpdateInstructor.fxml");
    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    public void loadTableData() throws SQLException {
        tblInstructor.setItems(FXCollections.observableArrayList(
                instructorBO.getAllInstructor().stream().map( instructorDTO->
                        new InstructorTM(
                                instructorDTO.getInstructorId(),
                                instructorDTO.getName(),
                                instructorDTO.getEmail(),
                                instructorDTO.getPhone(),
                                instructorDTO.getSpecialization()

                        )).toList()
        ));
        tblInstructor.refresh();
    }


}
