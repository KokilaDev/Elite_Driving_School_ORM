package lk.ijse.elite_driving_school_orm.controller.lesson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.CourseBO;
import lk.ijse.elite_driving_school_orm.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_orm.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.dto.InstructorDTO;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddLessonController implements Initializable {
    public AnchorPane ancAddLesson;
    public Button btnBack;
    public Label lblLessonId;
    public TextArea txtDescription;
    public ComboBox<String> cmbInstructor;
    public Label lblInstructor;
    public DatePicker dpDate;
    public TextField txtTime;
    public Button btnSave;
    public Button btnCancel;

    private final LessonBO lessonBO = BOFactory.getInstance().getBO(BOTypes.LESSON);
    private final InstructorBO instructorBO = BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);

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

        try{
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadNextId() throws SQLException {
        String nextId = lessonBO.getNextId();
        lblLessonId.setText(nextId);
    }

    public void loadInstructorIds() throws SQLException {
        List<InstructorDTO> instructorList = instructorBO.getAllInstructor();
        ObservableList<String> ids = FXCollections.observableArrayList();
        for (InstructorDTO instructorDTO : instructorList){
            ids.add(instructorDTO.getInstructorId());
        }

        cmbInstructor.setItems(ids);

        // ComboBox select ID Label set name
        cmbInstructor.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                for (InstructorDTO instructorDTO : instructorList){
                    if (instructorDTO.getInstructorId().equals(newVal)){
                        lblInstructor.setText(instructorDTO.getName());
                    }
                }
            }
        });

    }

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancAddLesson, "/view/lesson/MainLesson.fxml");
    }

    public void btnSave(ActionEvent actionEvent) {
        String lessonId = lblLessonId.getText();
        String description = txtDescription.getText();
        LocalDate date = dpDate.getValue();
        String time = txtTime.getText().toString();
        String instructor = cmbInstructor.getValue();

        LessonDTO lessonDTO = new LessonDTO(
                lessonId,
                description,
                date,
                time,
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
        cmbInstructor.setValue(null);
        lblInstructor.setText("--");

        btnSave.setDisable(false);
        btnCancel.setDisable(false);

        loadNextId();
        loadInstructorIds();
    }

    public void btnCancel(ActionEvent actionEvent) throws SQLException {
        resetPage();
    }
}
