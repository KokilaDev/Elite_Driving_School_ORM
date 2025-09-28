package lk.ijse.elite_driving_school_orm.controller.lesson;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddLessonController implements Initializable {
    public AnchorPane ancAddLesson;
    public Button btnBack;
    public Label lblLessonId;
    public TextArea txtDescription;
    public ComboBox<String> cmbCourse;
    public Label lblCourse;
    public ComboBox<String> cmbInstructor;
    public Label lblInstructor;
    public DatePicker dpDate;
    public TextField txtTime;
    public Button btnSave;
    public Button btnCancel;

    private final LessonBO lessonBO = BOFactory.getInstance().getBO(BOTypes.LESSON);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSave.setDisable(false);
        btnCancel.setDisable(false);
        btnBack.setDisable(false);

        try{
            loadNextId();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadNextId() throws SQLException {
        String nextId = lessonBO.getNextId();
        lblLessonId.setText(nextId);
    }

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancAddLesson, "/view/lesson/MainLesson.fxml");
    }

    public void btnSave(ActionEvent actionEvent) {
        String lessonId = lblLessonId.getText();
        String description = txtDescription.getText();
        String date = dpDate.getValue().toString();
        String time = txtTime.getText().toString();
        String course = cmbCourse.getValue();
        String instructor = cmbInstructor.getValue();

        LessonDTO lessonDTO = new LessonDTO(
                lessonId,
                description,
                date,
                time,
                course,
                instructor
        );

        try {
            lessonBO.saveLesson(lessonDTO);
            resetPage();
            new Alert(Alert.AlertType.INFORMATION, "Lesson saved successfully").show();
        } catch (DuplicateException e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Lesson save failed").show();
        }
    }

    private void resetPage() throws SQLException {
        txtDescription.clear();
        txtTime.clear();
        dpDate.setValue(null);
        cmbCourse.setValue(null);
        cmbInstructor.setValue(null);

        btnSave.setDisable(true);
        btnCancel.setDisable(true);

        loadNextId();
    }

    public void btnCancel(ActionEvent actionEvent) throws SQLException {
        resetPage();
    }
}
