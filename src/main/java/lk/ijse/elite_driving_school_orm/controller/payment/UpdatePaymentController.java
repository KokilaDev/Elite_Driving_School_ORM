package lk.ijse.elite_driving_school_orm.controller.payment;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class UpdatePaymentController {
    public AnchorPane ancUpdatePayment;
    public Button btnBack;
    public Label lblPaymentID;
    public ComboBox cmbStudent;
    public ListView courseListView;
    public TextField txtAmount;
    public DatePicker dpDate;
    public ComboBox cmbStatus;
    public Button btnUpdate;
    public Button btnCancel;

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancUpdatePayment, "/view/payment/MainPayment.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
    }

    public void btnCancel(ActionEvent actionEvent) {

    }
}
