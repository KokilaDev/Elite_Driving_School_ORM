package lk.ijse.elite_driving_school_orm.controller.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.CourseBO;
import lk.ijse.elite_driving_school_orm.bo.custom.StudentBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.dto.CourseDTO;
import lk.ijse.elite_driving_school_orm.dto.StudentDTO;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {
    public AnchorPane ancAddStudent;
    public Label lblStudentID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtNIC;
    public TextField txtEmail;
    public TextField txtContact;
    public DatePicker datePicker;
    public ListView courseListView;
    public ListView selectedCoursesListView;
    public Button btnAdd;
    public Button btnRemove;
    public Button btnSave;
    public Button btnCancel;
    public Button btnBack;

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);

    private List<CourseDTO> allCourses = new ArrayList<>();

    private final String namePattern = "^[A-Za-z ]+$";
    private final String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setDisable(false);
        btnRemove.setDisable(false);
        btnSave.setDisable(false);
        btnCancel.setDisable(false);
        btnBack.setDisable(false);
        courseListView.setDisable(false);
        selectedCoursesListView.setDisable(false);

        courseListView.getItems().addAll("Course A", "Course B", "Course C");
//        try {
//            List<CourseDTO> courses = courseBO.getAllCourses(); // getAllCourses() should return a list of CourseDTO
//            for (CourseDTO course : courses) {
//                courseListView.getItems().add(course.getName()); // or course.getId() + " - " + course.getName()
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Failed to load courses").show();
//        }

//        loadCoursesFromDB();

        courseListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            btnAdd.setDisable(newVal == null);
        });

        selectedCoursesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            btnRemove.setDisable(newVal == null);
        });

        try{
            loadNextId();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    private void loadCoursesFromDB() {
//        try {
//            allCourses = courseBO.getAllCourses();
//            courseListView.getItems().clear();
//            for (CourseDTO course : allCourses) {
//                // Display as "C001 - Driving Basics" or just name
//                courseListView.getItems().add(course.getId() + " - " + course.getName());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Failed to load courses").show();
//        }
//    }

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancAddStudent, "/view/student/MainStudent.fxml");
    }

    public void btnAdd(ActionEvent actionEvent) {
        String selectedCourse = (String) courseListView.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            selectedCoursesListView.getItems().add(selectedCourse);
            courseListView.getItems().remove(selectedCourse);
            btnAdd.setDisable(false);
        }
    }

    public void btnRemove(ActionEvent actionEvent) {
        String selectedCourse = (String) selectedCoursesListView.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            courseListView.getItems().add(selectedCourse);
            selectedCoursesListView.getItems().remove(selectedCourse);
            btnRemove.setDisable(false);
        }
    }

    public void btnSave(ActionEvent actionEvent) {
        String studentId = lblStudentID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String nic = txtNIC.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        LocalDate date = datePicker.getValue();

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = contact.matches(phonePattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #3867d6;");
        txtNIC.setStyle(txtNIC.getStyle() + ";-fx-border-color: #3867d6;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #3867d6;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #3867d6;");

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #EA2027;");
        }

        if (!isValidNic) {
            txtNIC.setStyle(txtNIC.getStyle() + ";-fx-border-color: #EA2027;");
        }

        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #EA2027;");
        }

        if (!isValidPhone) {
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #EA2027;");
        }

        StudentDTO studentDTO = new StudentDTO(
                studentId,
                name,
                address,
                nic,
                email,
                contact,
                date
        );

        if (isValidName && isValidNic && isValidEmail && isValidPhone) {
            try {

                studentBO.saveStudent(studentDTO);
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Student added successfully").show();

            } catch (DuplicateException e) {

                System.out.println(e.getMessage());
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            }catch (Exception e) {

                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to save student!").show();
            }
        }
    }

    public void btnCancel(ActionEvent actionEvent) throws SQLException {
        resetPage();
    }

    private void resetPage() throws SQLException {
        txtName.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtEmail.clear();
        txtContact.clear();
        datePicker.setValue(null);
        courseListView.getItems().addAll(selectedCoursesListView.getItems());
        selectedCoursesListView.getItems().clear();

        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        btnSave.setDisable(true);
        btnCancel.setDisable(true);
    }

    public void txtNameChange(KeyEvent keyEvent) {
        String name = txtName.getText();

        boolean isValidName = name.matches(namePattern);

        txtName.setStyle("-fx-border-color: " + (isValidName ? "#3867d6" : "#EA2027"));
    }

    private void loadNextId() throws SQLException {
        String nextId = studentBO.getNextId();
        lblStudentID.setText(nextId);

    }
}
