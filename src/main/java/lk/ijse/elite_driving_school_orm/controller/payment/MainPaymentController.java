package lk.ijse.elite_driving_school_orm.controller.payment;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class MainPaymentController {
    public AnchorPane ancPayment;
    public Button btnAddNewPayment;
    public TableView tblPayment;
    public TableColumn colPaymentID;
    public TableColumn colStudentID;
    public TableColumn colTotalAmount;
    public TableColumn colDate;
    public TableColumn colStatus;
    public Button btnUpdate;
    public Button btnDelete;

    public void btnAddNewPayment(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancPayment, "/view/payment/AddPayment.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancPayment, "/view/payment/UpdatePayment.fxml");
    }

    public void btnDelete(ActionEvent actionEvent) {

    }
}
