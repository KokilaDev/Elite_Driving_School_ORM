package lk.ijse.elite_driving_school_orm.controller.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.io.IOException;

public class AddStudentController {
    public AnchorPane ancAddStudent;
    public Label lblStudentID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtNIC;
    public TextField txtEmail;
    public TextField txtContact;
    public DatePicker datePicker;
    public ListView courseListView;
    public ListView selectedCoursesListView;
    public Button btnAdd;
    public Button btnRemove;
    public Button btnSave;
    public Button btnCancel;
    public Button btnBack;

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancAddStudent, "/view/student/MainStudent.fxml");
    }

    public void btnAdd(ActionEvent actionEvent) {
    }

    public void btnRemove(ActionEvent actionEvent) {
    }

    public void btnSave(ActionEvent actionEvent) {
    }

    public void btnCancel(ActionEvent actionEvent) {

    }
}
