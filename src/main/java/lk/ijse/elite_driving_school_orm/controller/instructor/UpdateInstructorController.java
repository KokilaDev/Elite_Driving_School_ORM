package lk.ijse.elite_driving_school_orm.controller.instructor;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class UpdateInstructorController {
    public AnchorPane ancUpdateInstructor;
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
    public Button btnUpdate;
    public Button btnCancel;

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancUpdateInstructor, "/view/instructor/MainInstructor.fxml");
    }

    public void btnAdd(ActionEvent actionEvent) {
    }

    public void btnRemove(ActionEvent actionEvent) {
    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnCancel(ActionEvent actionEvent) {
    }
}
