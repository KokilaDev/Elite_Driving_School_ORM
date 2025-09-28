package lk.ijse.elite_driving_school_orm.controller.instructor;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_orm.bo.custom.LessonBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.dto.CourseDTO;
import lk.ijse.elite_driving_school_orm.dto.InstructorDTO;
import lk.ijse.elite_driving_school_orm.dto.LessonDTO;
import lk.ijse.elite_driving_school_orm.dto.StudentDTO;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddInstructorController implements Initializable {
    public AnchorPane ancAddInstructor;
    public Button btnBack;
    public Label lblInstructorID;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtContact;
    public ComboBox<String> cmbSpecialty;
    public ListView<String> lessonsListView;
    public Button btnAdd;
    public Button btnRemove;
    public ListView<String> selectedLessonsListView;
    public Button btnSave;
    public Button btnCancel;

    private final InstructorBO instructorBO = BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);
    private final LessonBO lessonBO = BOFactory.getInstance().getBO(BOTypes.LESSON);

    private List<LessonDTO> allLessons = new ArrayList<>();

    private final String namePattern = "^[A-Za-z ]+$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    // Allow either digits or digits with two decimal places (if you actually need decimals). Change as required.
    private final String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbSpecialty.getItems().addAll( "Light Vehicle", "Heavy Vehicle", "Motorcycle",
                "Defensive Driving", "Emergency Vehicle", "Off-Road Vehicle", "Automatic Transmission", "Manual Transmission" );

        // initial state: no selections -> disable add/remove/save/cancel
        btnAdd.setDisable(false);
        btnRemove.setDisable(false);
        btnSave.setDisable(false);
        btnCancel.setDisable(false);
        btnBack.setDisable(false);
        lessonsListView.setDisable(false);
        selectedLessonsListView.setDisable(false);

        loadLessonsFromDB();

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
    }

    private void loadLessonsFromDB() {
        try {
            allLessons = lessonBO.getAllLessons();
            lessonsListView.getItems().clear();
            for (LessonDTO lesson : allLessons) {
                // Display as "L001 - Lesson_Description"
                lessonsListView.getItems().add(lesson.getLessonId() + " - " + lesson.getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load lessons").show();
        }
    }

    private void loadNextId() throws SQLException {
        String nextId = instructorBO.getNextId();
        lblInstructorID.setText(nextId);
    }

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancAddInstructor, "/view/instructor/MainInstructor.fxml");
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
        String instructorID = lblInstructorID.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String speciality = (String) cmbSpecialty.getValue();

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = contact.matches(phonePattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #3867d6;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #3867d6;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #3867d6;");

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #EA2027;");
        }

        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #EA2027;");
        }

        if (!isValidPhone) {
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #EA2027;");
        }

        // Build DTO (constructor ordering: id, name, address, nic, email, phone, regDate)
        InstructorDTO instructorDTO = new InstructorDTO(
                instructorID,
                name,
                email,
                contact,
                speciality
        );

//        if (isValidName && isValidEmail && isValidPhone) {
            try {
                instructorBO.saveInstructor(instructorDTO);
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Student added successfully").show();

            } catch (DuplicateException e) {

                System.out.println(e.getMessage());
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }catch (Exception e) {

                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to save student!").show();
            }
//        }
    }

    private void resetPage() throws SQLException {
        txtName.clear();
        txtEmail.clear();
        txtContact.clear();
        cmbSpecialty.setValue(null);

        // Put selected courses back to courseListView
        lessonsListView.getItems().addAll(selectedLessonsListView.getItems());
        selectedLessonsListView.getItems().clear();

        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        btnSave.setDisable(true);
        btnCancel.setDisable(true);

        loadNextId();
    }

    public void btnCancel(ActionEvent actionEvent) throws SQLException {
        resetPage();
    }

    public void txtNameChange(KeyEvent keyEvent) {
        String name = txtName.getText();

        boolean isValidName = name.matches(namePattern);

        txtName.setStyle("-fx-border-color: " + (isValidName ? "#3867d6" : "#EA2027"));

        btnSave.setDisable(!isValidName); // optional immediate feedback
    }

    public void txtEmailChange(KeyEvent keyEvent) {
        String email = txtEmail.getText();

        boolean isValidEmail = email.matches(emailPattern);

        txtEmail.setStyle("-fx-border-color: " + (isValidEmail ? "#3867d6" : "#EA2027"));

        btnSave.setDisable(!isValidEmail); // optional immediate feedback
    }

    public void txtContactChange(KeyEvent keyEvent) {
        String contact = txtContact.getText();

        boolean isValidContact = contact.matches(phonePattern);

        txtContact.setStyle("-fx-border-color: " + (isValidContact ? "#3867d6" : "#EA2027"));

        btnSave.setDisable(!isValidContact); // optional immediate feedback
    }
}
