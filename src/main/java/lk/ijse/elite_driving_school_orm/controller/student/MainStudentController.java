package lk.ijse.elite_driving_school_orm.controller.student;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.dto.tm.StudentTM;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.io.IOException;

public class MainStudentController {

//    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public TableView<StudentTM> tblStudent;

    public TableColumn<StudentTM, String> colStudentID;
    public TableColumn<StudentTM, String> colName;
    public TableColumn<StudentTM, String> colAddress;
    public TableColumn<StudentTM, String> colNIC;
    public TableColumn<StudentTM, String> colEmail;
    public TableColumn<StudentTM, String> colContact;
    public TableColumn<StudentTM, String> colRegDate;

    private final String namePattern = "^[A-Za-z ]+$";
    private final String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

    public Button btnUpdate;
    public Button btnDelete;
    public Button btnAddNewStudent;
    public AnchorPane ancStudentForm;

    public void btnAddNewStudent(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancStudentForm, "/view/student/AddStudent.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancStudentForm, "/view/student/UpdateStudent.fxml");
    }

    public void btnDelete(ActionEvent actionEvent) {
    }
}
