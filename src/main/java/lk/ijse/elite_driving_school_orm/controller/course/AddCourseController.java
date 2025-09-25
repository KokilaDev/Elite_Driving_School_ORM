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
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCourseController implements Initializable {
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

    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);
    private final LessonBO lessonBO = BOFactory.getInstance().getBO(BOTypes.LESSON);

    private ObservableList<String> availableLessons = FXCollections.observableArrayList();
    private ObservableList<String> selectedLessons = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setDisable(false);
        btnRemove.setDisable(false);
        btnSave.setDisable(false);
        btnCancel.setDisable(false);
        btnBack.setDisable(false);
        lessonsListView.setDisable(false);
        selectedLessonsListView.setDisable(false);

//        lessonsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        selectedLessonsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        lessonsListView.getItems().addAll("Lesson 1", "Lesson 2", "Lesson 3");

//        try {
//            List<LessonDTO> lessons = lessonBO.getAllLessons();
//            for (LessonDTO lesson : lessons) {
//                lessonsListView.getItems().add(lesson.getName());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Failed to load lessons.").show();
//        }

        lessonsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            btnAdd.setDisable(newVal == null);
        });

        selectedLessonsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            btnRemove.setDisable(newVal == null);
        });

        try{
            loadNextId();
        }catch (Exception e){
            e.printStackTrace();
        }

        loadLessons();
    }

    private void loadNextId() {
        try {
            String nextId = courseBO.getNextId(); // implement this in BO
            lblCourseID.setText(nextId);
        } catch (Exception e) {
            e.printStackTrace();
            lblCourseID.setText("C001"); // fallback
        }
    }

    private void loadLessons() {
        try {
            List<LessonDTO> lessons = lessonBO.getAllLessons();
            for (LessonDTO lesson : lessons) {
                availableLessons.add(lesson.getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load lessons.").show();
        }
    }

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancAddCourse, "/view/course/MainCourse.fxml");
    }

    public void btnAdd(ActionEvent actionEvent) {
        String selectedCourse = (String) lessonsListView.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            selectedLessonsListView.getItems().add(selectedCourse);
            lessonsListView.getItems().remove(selectedCourse);
            btnAdd.setDisable(false);
        }
    }

    public void btnRemove(ActionEvent actionEvent) {
        String selectedCourse = (String) selectedLessonsListView.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            lessonsListView.getItems().add(selectedCourse);
            selectedLessonsListView.getItems().remove(selectedCourse);
            btnRemove.setDisable(false);
        }
    }

    public void btnSave(ActionEvent actionEvent) {
        // Get input values from UI
        String courseId = lblCourseID.getText();
        String name = txtName.getText();
        String durationText = txtDuration.getText();
        String feeText = txtFee.getText();

        // Basic validation
        if (name.isEmpty() || durationText.isEmpty() || feeText.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
            return;
        }

        // Parse numeric values safely
        int duration;
        double fee;
        try {
            duration = Integer.parseInt(durationText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Duration must be a number").show();
            return;
        }

        try {
            fee = Double.parseDouble(feeText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Fee must be a number").show();
            return;
        }

        // Convert selected lessons to LessonDTOs
        List<LessonDTO> lessonDTOs = new ArrayList<>();
        for (String lessonName : selectedLessons) {
            try {
                LessonDTO lesson = (LessonDTO) lessonBO.getAllLessons();
                lessonDTOs.add(lesson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Create CourseDTO
        CourseDTO courseDTO = new CourseDTO(courseId, name, duration, fee);

        try {
            boolean isSaved = courseBO.saveCourse(courseDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Course saved successfully!").show();
                clearFields();
                loadNextId();
                selectedLessons.clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save course!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error saving course: " + e.getMessage()).show();
        }
    }

    // Optional helper method to clear input fields
    private void clearFields() {
        txtName.clear();
        txtDuration.clear();
        txtFee.clear();
        selectedLessons.clear();
        availableLessons.clear();
        loadLessons();
    }


    public void btnCancel(ActionEvent actionEvent) {
        clearFields();
    }
}
