package lk.ijse.elite_driving_school_orm.controller.instructor;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class MainInstructorController {
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

    public void btnAddNewInstructor(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancInstructor, "/view/instructor/AddInstructor.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancInstructor, "/view/instructor/UpdateInstructor.fxml");
    }

    public void btnDelete(ActionEvent actionEvent) {
    }
}
