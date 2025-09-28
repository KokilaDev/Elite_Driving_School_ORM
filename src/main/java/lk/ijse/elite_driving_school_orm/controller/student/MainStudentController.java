package lk.ijse.elite_driving_school_orm.controller.student;

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
import lk.ijse.elite_driving_school_orm.bo.custom.StudentBO;
import lk.ijse.elite_driving_school_orm.bo.exception.InUseException;
import lk.ijse.elite_driving_school_orm.dto.tm.StudentTM;
import lk.ijse.elite_driving_school_orm.util.AuthUtil;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainStudentController implements Initializable {

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private StudentTM selectedStudent;

    public TableView<StudentTM> tblStudent;

    public TableColumn<StudentTM, String> colStudentID;
    public TableColumn<StudentTM, String> colName;
    public TableColumn<StudentTM, String> colEmail;
    public TableColumn<StudentTM, String> colContact;
    public TableColumn<StudentTM, String> colAddress;
    public TableColumn<StudentTM, String> colNIC;
    public TableColumn<StudentTM, LocalDate> colRegDate;

    public Button btnUpdate;
    public Button btnDelete;
    public Button btnAddNewStudent;
    public AnchorPane ancStudentForm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));

        try {
            resetPage();
        } catch (SQLException e) {

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

        tblStudent.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
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

        btnAddNewStudent.setDisable(false);
    }

    public void loadTableData() throws SQLException {
        tblStudent.setItems(FXCollections.observableArrayList(
                studentBO.getAllStudent().stream().map(studentDTO ->
                        new StudentTM(
                                studentDTO.getStudentId(),
                                studentDTO.getName(),
                                studentDTO.getAddress(),
                                studentDTO.getNic(),
                                studentDTO.getEmail(),
                                studentDTO.getPhone(),
                                studentDTO.getRegDate()
                        )).toList()
        ));
        tblStudent.refresh();
    }

    public void btnAddNewStudent(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancStudentForm, "/view/student/AddStudent.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
//        if (selectedStudent != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student/UpdateStudent.fxml"));
                Parent root = loader.load();

                UpdateStudentController controller = loader.getController();

                controller.setStudentData(selectedStudent);

                ancStudentForm.getChildren().clear();
                ancStudentForm.getChildren().add(root);

//                AnchorPane.setTopAnchor(root, 0.0);
//                AnchorPane.setLeftAnchor(root, 0.0);
//                AnchorPane.setRightAnchor(root, 0.0);
//                AnchorPane.setBottomAnchor(root, 0.0);

            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
//        else {
//            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a student to update!");
//            alert.show();
//        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            try {
                boolean isDeleted = studentBO.deleteStudent(selectedStudent.getStudentId());
                // You may check isDeleted if your BO returns boolean to indicate success
//                if (isDeleted) {
                resetPage();
                new Alert(
                        Alert.AlertType.INFORMATION, "Student deleted successfully."
                ).show();
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Fail to delete student.").show();
//
//                }
            } catch (InUseException e) {

                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Student not deleted").show();
            }
        }
    }

    public void onClickTable(javafx.scene.input.MouseEvent mouseEvent) {
        selectedStudent = tblStudent.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
