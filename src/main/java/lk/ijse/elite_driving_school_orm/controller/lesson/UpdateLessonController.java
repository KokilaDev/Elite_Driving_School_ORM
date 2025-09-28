package lk.ijse.elite_driving_school_orm.controller.lesson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_orm.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_orm.dto.InstructorDTO;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;
import lk.ijse.elite_driving_school_orm.dto.tm.LessonTM;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateLessonController implements Initializable {
    public AnchorPane ancUpdateLesson;
    public Button btnBack;
    public Label lblLessonId;
    public TextArea txtDescription;
    public ComboBox<String> cmbInstructor;
    public Label lblInstructor;
    public DatePicker dpDate;
    public TextField txtTime;
    public Button btnUpdate;
    public Button btnCancel;

    private final LessonBO lessonBO = BOFactory.getInstance().getBO(BOTypes.LESSON);
    private final InstructorBO instructorBO = BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);
    private LessonTM selectedLesson;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUpdate.setDisable(true);
        btnCancel.setDisable(true);
        btnBack.setDisable(false);

        // Load Instructor IDs at scene load
        try {
            loadInstructorIds();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load instructor IDs!").show();
        }
    }

    public void setLessonData(LessonTM lesson) {
        this.selectedLesson = lesson;

        lblLessonId.setText(lesson.getLessonId());
        txtDescription.setText(lesson.getDescription());
        cmbInstructor.setValue(lesson.getInstructorId());
        dpDate.setValue(lesson.getDate());
        txtTime.setText(lesson.getTime());

        btnUpdate.setDisable(false);
        btnCancel.setDisable(false);
    }

    public void loadInstructorIds() throws SQLException {
        List<InstructorDTO> instructorList = instructorBO.getAllInstructor();
        ObservableList<String> ids = FXCollections.observableArrayList();
        for (InstructorDTO instructorDTO : instructorList) {
            ids.add(instructorDTO.getInstructorId());
        }

        cmbInstructor.setItems(ids);

        // ComboBox select ID Label set name
        cmbInstructor.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                for (InstructorDTO instructorDTO : instructorList) {
                    if (instructorDTO.getInstructorId().equals(newVal)) {
                        lblInstructor.setText(instructorDTO.getName());
                    }
                }
            }
        });

        System.out.println("Instructor List: " + instructorList); // debug check
    }

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancUpdateLesson, "/view/lesson/MainLesson.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        if (selectedLesson == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a lesson first!").show();
            return;
        }

        String lessonId = lblLessonId.getText();
        String description = txtDescription.getText();
        String instructorId = cmbInstructor.getValue();
        LocalDate date = dpDate.getValue();
        String time = txtTime.getText();

        if (description.isEmpty() || date == null || time.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields must be filled!").show();
            return;
        }

        LessonDTO lessonDTO = new LessonDTO(
                lessonId,
                description,
                date,
                time,
                instructorId
        );

        try {
            lessonBO.updateLesson(lessonDTO);
            resetPage();
            new Alert(Alert.AlertType.INFORMATION, "Lesson updated successfully").show();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to update lesson!").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Unexpected error occurred!").show();
        }
    }

    private void resetPage() throws SQLException {
        txtDescription.clear();
        cmbInstructor.setValue(null);
        lblInstructor.setText("--");
        dpDate.setValue(null);
        txtTime.clear();

        selectedLesson = null;

        btnUpdate.setDisable(true);
        btnCancel.setDisable(true);

        loadInstructorIds();
    }

    public void btnCancel(ActionEvent actionEvent) throws SQLException {
        resetPage();
    }
}
