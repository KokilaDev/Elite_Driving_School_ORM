package lk.ijse.elite_driving_school_orm.controller.instructor;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_orm.controller.student.UpdateStudentController;
import lk.ijse.elite_driving_school_orm.dto.tm.InstructorTM;
import lk.ijse.elite_driving_school_orm.dto.tm.StudentTM;
import lk.ijse.elite_driving_school_orm.entity.Instructor;
import lk.ijse.elite_driving_school_orm.util.AuthUtil;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.io.IOException;
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
    private InstructorTM selectedInstructor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        try {
            resetPage();
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }

        boolean isAdmin = AuthUtil.isAdmin();
        if (!isAdmin) {
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
        }

        // disable update/delete until a row is selected
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        tblInstructor.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            } else {
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        });
    }

    private void resetPage() throws SQLException {
        loadTableData();

//        btnUpdate.setDisable(false);
//        btnDelete.setDisable(false);

        btnAddNewInstructor.setDisable(false);
    }

    public void btnAddNewInstructor(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancInstructor, "/view/instructor/AddInstructor.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/instructor/UpdateInstructor.fxml"));
            Parent root = loader.load();

            UpdateInstructorController controller = loader.getController();

            controller.setInstructorData(selectedInstructor);

            ancInstructor.getChildren().clear();
            ancInstructor.getChildren().add(root);

//                AnchorPane.setTopAnchor(root, 0.0);
//                AnchorPane.setLeftAnchor(root, 0.0);
//                AnchorPane.setRightAnchor(root, 0.0);
//                AnchorPane.setBottomAnchor(root, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void onClickTable(javafx.scene.input.MouseEvent mouseEvent) {
        selectedInstructor = (InstructorTM) tblInstructor.getSelectionModel().getSelectedItem();

        if (selectedInstructor != null) {
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
