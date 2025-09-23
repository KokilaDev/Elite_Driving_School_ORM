package lk.ijse.elite_driving_school_orm.controller.course;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class AddCourseController {
    public AnchorPane ancAddCourse;
    public Button btnBack;
    public Label lblCourseID;
    public TextField txtName;
    public TextField txtDuration;
    public TextField txtFee;
    public ListView lessonsListView;
    public Button btnAdd;
    public Button btnRemove;
    public ListView selectedLessonsListView;
    public Button btnSave;
    public Button btnCancel;

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancAddCourse, "/view/course/MainCourse.fxml");
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
