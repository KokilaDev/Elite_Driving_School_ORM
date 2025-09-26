package lk.ijse.elite_driving_school_orm.controller.course;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.CourseBO;
import lk.ijse.elite_driving_school_orm.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_orm.dto.CourseDTO;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;
import lk.ijse.elite_driving_school_orm.dto.tm.CourseTM;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateCourseController implements Initializable {
    public AnchorPane ancUpdateCourse;
    public Button btnBack;
    public Label lblCourseID;
    public TextField txtName;
    public TextField txtDuration;
    public TextField txtFee;
    public ListView lessonsListView;
    public Button btnAdd;
    public Button btnRemove;
    public ListView selectedLessonsListView;
    public Button btnUpdate;
    public Button btnCancel;

    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);

    private ObservableList<String> availableLessons = FXCollections.observableArrayList();
    private ObservableList<String> selectedLessons = FXCollections.observableArrayList();

    private CourseTM courseTM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lessonsListView.setItems(availableLessons);
        selectedLessonsListView.setItems(selectedLessons);

        lessonsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> btnAdd.setDisable(newVal == null));
        selectedLessonsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> btnRemove.setDisable(newVal == null));

        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
    }

    public void setCourseData(CourseTM course) {
        if (course == null) return;

        this.courseTM = course;

        lblCourseID.setText(course.getCourseId());
        txtName.setText(course.getName());
        txtDuration.setText(String.valueOf(course.getDuration()));
        txtFee.setText(String.valueOf(course.getFee()));

        // Load lessons (you can load actual lesson names via BO)
        try {
            List<LessonDTO> allLessons = courseBO.getAllLessons(); // implement in BO
            for (LessonDTO lesson : allLessons) {
                availableLessons.add(lesson.getLessonId());
            }

            // Load selected lessons for this course
            List<LessonDTO> courseLessons = courseBO.getLessonsByCourseId(course.getCourseId());
            for (LessonDTO lesson : courseLessons) {
                selectedLessons.add(lesson.getLessonId());
                availableLessons.remove(lesson.getLessonId());
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load lessons.").show();
        }
    }

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancUpdateCourse, "/view/course/MainCourse.fxml");
    }

    public void btnAdd(ActionEvent actionEvent) {
        List<String> selected = new ArrayList<>(lessonsListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            selectedLessonsListView.getItems().addAll(selected);
            lessonsListView.getItems().removeAll(selected);
        }
    }
    public void btnRemove(ActionEvent actionEvent) {
        List<String> selected = new ArrayList<>(selectedLessonsListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            lessonsListView.getItems().addAll(selected);
            selectedLessonsListView.getItems().removeAll(selected);
        }
    }

    public void btnUpdate(ActionEvent actionEvent) {
        String id = lblCourseID.getText();
        String name = txtName.getText();
        int durationText = Integer.parseInt(txtDuration.getText());
        Double feeText = Double.valueOf(txtFee.getText());

//        if (name.isEmpty() || durationText.isEmpty() || feeText.isEmpty() || selectedLessons.isEmpty()) {
//            new Alert(Alert.AlertType.WARNING, "Please fill all fields and select at least one lesson!").show();
//            return;
//        }

//        int duration;
//        double fee;
//        try {
//            duration = Integer.parseInt(durationText);
//            fee = Double.parseDouble(feeText);
//        } catch (NumberFormatException e) {
//            new Alert(Alert.AlertType.ERROR, "Duration and Fee must be numbers").show();
//            return;
//        }

        // Convert selected lessons to LessonDTO
//        List<LessonDTO> lessonDTOs = new ArrayList<>();
//        for (String lessonName : selectedLessons) {
//            try {
//                LessonDTO lesson = (LessonDTO) courseBO.getLessonsById(); // implement in BO
//                lessonDTOs.add(lesson);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

//        CourseDTO courseDTO = new CourseDTO(courseTM.getCourseId());

        CourseDTO courseDTO = new CourseDTO(
                id,
                name,
                durationText,
                feeText
        );



        try {
            boolean updated = courseBO.updateCourse(courseDTO);
//            if (updated) {
//                new Alert(Alert.AlertType.INFORMATION, "Course updated successfully!").show();
//                btnBack(actionEvent); // navigate back
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Failed to update course!").show();
//            }
            resetPage();
            new Alert(Alert.AlertType.CONFIRMATION, "Course updated successfully!").show();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error updating course: " + e.getMessage()).show();
        }
    }

    public void btnCancel(ActionEvent actionEvent) {
        setCourseData(courseTM);
    }

    private void resetPage() throws SQLException {
        txtName.clear();
        txtFee.clear();
        txtDuration.clear();

//        courseListView.getItems().addAll(selectedCoursesListView.getItems());
//        selectedCoursesListView.getItems().clear();
//
//        selectedStudent = null;

        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        btnUpdate.setDisable(true);
        btnCancel.setDisable(true);
    }
}
