package lk.ijse.elite_driving_school_orm.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.StudentBO;
import lk.ijse.elite_driving_school_orm.dto.tm.StudentTM;

public class StudentController {
    public AnchorPane ancStudent;
    public Label lblStudentID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtNIC;
    public TextField txtEmail;
    public TextField txtContact;
    public DatePicker datePicker;

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

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

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;

    public void btnSave(ActionEvent actionEvent) {
    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnDelete(ActionEvent actionEvent) {
    }

    public void btnClear(ActionEvent actionEvent) {
    }
}
