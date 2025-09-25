package lk.ijse.elite_driving_school_orm.controller.student;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.CourseBO;
import lk.ijse.elite_driving_school_orm.bo.custom.StudentBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.dto.CourseDTO;
import lk.ijse.elite_driving_school_orm.dto.StudentDTO;
import lk.ijse.elite_driving_school_orm.dto.tm.StudentTM;
import lk.ijse.elite_driving_school_orm.model.CourseModel;
import lk.ijse.elite_driving_school_orm.model.StudentModel;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateStudentController implements Initializable {
    public AnchorPane ancUpdateStudent;
    public Label lblStudentID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtNIC;
    public TextField txtEmail;
    public TextField txtContact;
    public DatePicker datePicker;
    public ListView<String> courseListView;
    public ListView<String> selectedCoursesListView;
    public Button btnAdd;
    public Button btnRemove;
    public Button btnUpdate;
    public Button btnCancel;
    public Button btnBack;

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);

    private StudentTM selectedStudent;
    private List<String> allCourses;
    private List<String> studentCourses;

    private final String namePattern = "^[A-Za-z ]+$";
    private final String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        btnUpdate.setDisable(true);
        btnCancel.setDisable(true);
        btnBack.setDisable(false);

        courseListView.setDisable(false);
        selectedCoursesListView.setDisable(false);

//        courseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        selectedCoursesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

//        courseListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
//            btnAdd.setDisable(newVal == null);
//        });
//
//        selectedCoursesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
//            btnRemove.setDisable(newVal == null);
//        });

//        allCourses = CourseModel.getAllCourseNames();
//        courseListView.getItems().setAll(allCourses);
//
//        courseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        selectedCoursesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void setStudentData(StudentTM student) {
        this.selectedStudent = student;

        lblStudentID.setText(student.getStudentId());
        txtName.setText(student.getName());
        txtEmail.setText(student.getEmail());
        txtContact.setText(student.getPhone());
        txtAddress.setText(student.getAddress());
        txtNIC.setText(student.getNic());
        datePicker.setValue(student.getRegDate());

//        LocalDate regDate = student.getRegDate();
//        if (regDate != null) {
//            datePicker.setValue(regDate);
//        } else {
//            datePicker.setValue(null);
//        }

        // enable update/cancel now that data is loaded
        btnUpdate.setDisable(false);
        btnCancel.setDisable(false);

        // TODO: Load courses from DB if needed and set courseListView and selectedCoursesListView
//        studentCourses = CourseModel.getCoursesForStudent(student.getStudentId());
//        selectedCoursesListView.getItems().setAll(studentCourses);
//
//        allCourses = CourseModel.getAllCourseNames();
//        allCourses.removeAll(studentCourses);
//        courseListView.getItems().setAll(allCourses);
    }

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancUpdateStudent, "/view/student/MainStudent.fxml");
    }

    public void btnAdd(ActionEvent actionEvent) {
        List<String> selected = new ArrayList<>(courseListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            selectedCoursesListView.getItems().addAll(selected);
            courseListView.getItems().removeAll(selected);
        }
    }
    public void btnRemove(ActionEvent actionEvent) {
        List<String> selected = new ArrayList<>(selectedCoursesListView.getSelectionModel().getSelectedItems());
        if (!selected.isEmpty()) {
            courseListView.getItems().addAll(selected);
            selectedCoursesListView.getItems().removeAll(selected);
        }
    }

    public void btnUpdate(ActionEvent actionEvent) throws SQLException {
        if (selectedStudent == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a student first!").show();
            return;
        }

        String name = txtName.getText();
        String address = txtAddress.getText();
        String nic = txtNIC.getText();
        String email = txtEmail.getText();
        String phone = txtContact.getText();
        LocalDate date = datePicker.getValue();
        String studentId = lblStudentID.getText();

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

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

        // Match constructor order: id, name, address, nic, email, phone, regDate
        StudentDTO studentDTO = new StudentDTO(
                studentId,
                name,
                email,
                phone,
                address,
                nic,
                date
        );

        if (isValidName && isValidNic && isValidEmail && isValidPhone) {
            try {
                studentBO.updateStudent(studentDTO);
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

//        if (selectedStudent != null) {
//            String id = lblStudentID.getText();
//            String stuname = txtName.getText();
//            String stuaddress = txtAddress.getText();
//            String stunic = txtNIC.getText();
//            String stuemail = txtEmail.getText();
//            String contact = txtContact.getText();
//            LocalDate regDate = datePicker.getValue();
//
//            List<String> updatedCourses = new ArrayList<>(selectedCoursesListView.getItems());
//
//            boolean studentUpdated = studentBO.updateStudent(new StudentDTO(
//                    id, name, email, contact, address, nic,
//                    regDate != null ? regDate.toString() : null
//            ));
//
//            boolean coursesUpdated = CourseModel.updateStudentCourses(id, updatedCourses);
//
//            if (studentUpdated && coursesUpdated) {
//                new Alert(Alert.AlertType.INFORMATION, "Student and courses updated!").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Update failed!").show();
//            }
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Please select a student first!").show();
//        }
//        try {
//            boolean studentUpdated = studentBO.updateStudent(studentDTO);
//
//            List<String> updatedCourses = new ArrayList<>(selectedCoursesListView.getItems());
//            boolean courseUpdated = CourseModel.updateStudentCourses(studentId, updatedCourses);
//
//            if (studentUpdated && courseUpdated) {
//                new Alert(Alert.AlertType.INFORMATION, "Student updated successfully").show();
//                resetPage();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Fail to update student!").show();
//            }
//        } catch (DuplicateException e) {
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//
//        }
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

        selectedStudent = null;

        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        btnUpdate.setDisable(true);
        btnCancel.setDisable(true);
    }

}
