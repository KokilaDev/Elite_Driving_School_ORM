package lk.ijse.elite_driving_school_orm.controller.instructor;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.InstructorBO;
import lk.ijse.elite_driving_school_orm.bo.exception.DuplicateException;
import lk.ijse.elite_driving_school_orm.dto.InstructorDTO;
import lk.ijse.elite_driving_school_orm.dto.StudentDTO;
import lk.ijse.elite_driving_school_orm.dto.tm.InstructorTM;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateInstructorController implements Initializable {
    public AnchorPane ancUpdateInstructor;
    public Button btnBack;
    public Label lblInstructorID;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtContact;
    public ComboBox cmbSpecialty;
    public ListView lessonsListView;
    public Button btnAdd;
    public Button btnRemove;
    public ListView selectedLessonsListView;
    public Button btnUpdate;
    public Button btnCancel;

    private final InstructorBO instructorBO = BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);
    private InstructorTM selectedInstructor;

    private final String namePattern = "^[A-Za-z ]+$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        btnUpdate.setDisable(true);
        btnCancel.setDisable(true);
        btnBack.setDisable(false);

        lessonsListView.setDisable(false);
        selectedLessonsListView.setDisable(false);

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

    public void setInstructorData(InstructorTM instructor) {
        this.selectedInstructor = instructor;

        lblInstructorID.setText(instructor.getInstructorId());
        txtName.setText(instructor.getName());
        txtEmail.setText(instructor.getEmail());
        txtContact.setText(instructor.getPhone());
        cmbSpecialty.setValue(instructor.getSpecialization());

        // enable update/cancel now that data is loaded
        btnUpdate.setDisable(false);
        btnCancel.setDisable(false);

    }

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancUpdateInstructor, "/view/instructor/MainInstructor.fxml");
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
        if (selectedInstructor == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a student first!").show();
            return;
        }

        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtContact.getText();
        String speciality = (String) cmbSpecialty.getValue();
        String studentId = lblInstructorID.getText();

        boolean isValidName = name.matches(namePattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

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

        // Match constructor order: id, name, address, nic, email, phone, regDate
        InstructorDTO instructorDTO = new InstructorDTO(
                studentId,
                name,
                email,
                phone,
                speciality
        );

        if (isValidName && isValidEmail && isValidPhone) {
            try {
                instructorBO.updateInstructor(instructorDTO);
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Instructor added successfully").show();
            } catch (DuplicateException e) {
                System.out.println(e.getMessage());
//                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                new Alert(Alert.AlertType.ERROR,"fill save").show();
            }catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to save instructor!").show();
            }
        }

    }

    private void resetPage() {
        txtName.clear();
        txtEmail.clear();
        txtContact.clear();
        cmbSpecialty.setValue(null);

        lessonsListView.getItems().addAll(selectedLessonsListView.getItems());
        selectedLessonsListView.getItems().clear();

        selectedInstructor = null;

        btnAdd.setDisable(true);
        btnRemove.setDisable(true);
        btnUpdate.setDisable(true);
        btnCancel.setDisable(true);
    }

    public void btnCancel(ActionEvent actionEvent) {
        resetPage();
    }

    public void txtNameChange(KeyEvent keyEvent) {
        String name = txtName.getText();

        boolean isValidName = name.matches(namePattern);

        txtName.setStyle("-fx-border-color: " + (isValidName ? "#3867d6" : "#EA2027"));

        btnUpdate.setDisable(!isValidName); // optional immediate feedback
    }

    public void txtEmailChange(KeyEvent keyEvent) {
        String email = txtEmail.getText();

        boolean isValidEmail = email.matches(emailPattern);

        txtEmail.setStyle("-fx-border-color: " + (isValidEmail ? "#3867d6" : "#EA2027"));

        btnUpdate.setDisable(!isValidEmail); // optional immediate feedback
    }

    public void txtContactChange(KeyEvent keyEvent) {
        String contact = txtContact.getText();

        boolean isValidContact = contact.matches(phonePattern);

        txtContact.setStyle("-fx-border-color: " + (isValidContact ? "#3867d6" : "#EA2027"));

        btnUpdate.setDisable(!isValidContact); // optional immediate feedback
    }
}
