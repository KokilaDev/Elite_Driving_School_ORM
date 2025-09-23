package lk.ijse.elite_driving_school_orm.controller.lesson;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class UpdateLessonController {
    public AnchorPane ancUpdateLesson;
    public Button btnBack;
    public TextField txtLessonId;
    public TextArea txtDescription;
    public ComboBox cmbCourse;
    public Label lblCourse;
    public ComboBox cmbInstructor;
    public Label lblInstructor;
    public DatePicker dpDate;
    public TextField txtTime;
    public Button btnUpdate;
    public Button btnCancel;

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancUpdateLesson, "/view/lesson/MainLesson.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnCancel(ActionEvent actionEvent) {

    }
}
