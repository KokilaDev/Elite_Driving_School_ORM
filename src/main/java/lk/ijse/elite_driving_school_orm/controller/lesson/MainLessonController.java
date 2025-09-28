package lk.ijse.elite_driving_school_orm.controller.lesson;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_orm.dto.tm.LessonTM;
import lk.ijse.elite_driving_school_orm.util.AuthUtil;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainLessonController implements Initializable {

    private final LessonBO lessonBO = BOFactory.getInstance().getBO(BOTypes.LESSON);
    private LessonTM lessonTM;

    public AnchorPane ancLesson;
    public Button btnAddNewLesson;
    public TableView<LessonTM> tblLesson;
    @FXML
    public TableColumn<LessonTM, String> colLessonID;
    @FXML
    public TableColumn<LessonTM, String> colDescription;
    @FXML
    public TableColumn<LessonTM, String> colDate;
    @FXML
    public TableColumn<LessonTM, String> colTime;
    @FXML
    public TableColumn<LessonTM, String> colCourseID;
    @FXML
    public TableColumn<LessonTM, String> colInstructorID;
    public Button btnUpdate;
    public Button btnDelete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colLessonID.setCellValueFactory(new PropertyValueFactory<>("lessonId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colInstructorID.setCellValueFactory(new PropertyValueFactory<>("instructorId"));

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

        tblLesson.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
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

        btnAddNewLesson.setDisable(false);
    }

    private void loadTableData() throws SQLException {
        tblLesson.setItems(FXCollections.observableArrayList(
                lessonBO.getAllLessons().stream().map(lessonDTO ->
                        new LessonTM(
                                lessonDTO.getLessonId(),
                                lessonDTO.getDescription(),
                                lessonDTO.getDate(),
                                lessonDTO.getTime(),
                                lessonDTO.getCourseId(),
                                lessonDTO.getInstructorId()
                        )).toList()
        ));
        tblLesson.refresh();
    }

    public void btnAddNewLesson(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancLesson, "/view/lesson/AddLesson.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancLesson, "/view/lesson/UpdateLesson.fxml");
    }

    public void btnDelete(ActionEvent actionEvent) {

    }

//    public void onClickTable(javafx.scene.input.MouseEvent mouseEvent) {
//        selectedLesson = tblLesson.getSelectionModel().getSelectedItem();
//
//        if (selectedLesson != null) {
//            btnUpdate.setDisable(false);
//            btnDelete.setDisable(false);
//        }
//    }
}
