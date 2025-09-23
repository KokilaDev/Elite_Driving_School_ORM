package lk.ijse.elite_driving_school_orm.controller.course;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class MainCourseController {

    public AnchorPane ancCourse;
    public Button btnAddNewCourse;
    public TableView tblCourse;
    public TableColumn colCourseID;
    public TableColumn colName;
    public TableColumn colFee;
    public TableColumn colDuration;
    public Button btnUpdate;
    public Button btnDelete;

    public void btnAddNewCourse(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancCourse, "/view/course/AddCourse.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancCourse, "/view/course/UpdateCourse.fxml");
    }

    public void btnDelete(ActionEvent actionEvent) {

    }
}
