package lk.ijse.elite_driving_school_orm.controller.instructor;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class AddInstructorController {
    public AnchorPane ancAddInstructor;
    public Button btnBack;
    public Label lblInstructorID;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtContact;
    public ComboBox cmbSpecialty;
    public ListView lessonsListView;
    public Button btnAdd;
    public Button btnRemove;
    public ListView selectedLessonsListView;
    public Button btnSave;
    public Button btnCancel;

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancAddInstructor, "/view/instructor/MainInstructor.fxml");
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
