package lk.ijse.elite_driving_school_orm.controller.lesson;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class MainLessonController {

    public AnchorPane ancLesson;
    public Button btnAddNewLesson;
    public TableView tblLesson;
    public TableColumn colLessonID;
    public TableColumn colDescription;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colCourseID;
    public TableColumn colInstructorID;
    public Button btnUpdate;
    public Button btnDelete;

    public void btnAddNewLesson(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancLesson, "/view/lesson/AddLesson.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancLesson, "/view/lesson/UpdateLesson.fxml");
    }

    public void btnDelete(ActionEvent actionEvent) {

    }
}
