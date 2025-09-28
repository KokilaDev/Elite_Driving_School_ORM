package lk.ijse.elite_driving_school_orm.controller.payment;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

public class AddPaymentController {
    public AnchorPane ancAddPayment;
    public Button btnBack;
    public Label lblPaymentID;
    public ComboBox cmbStudent;
    public ListView courseListView;
    public Label lblAmount;
    public TextField txtAmountPaid;
    public Label lblDate;
    public ComboBox cmbStatus;
    public Button btnSave;
    public Button btnCancel;

    public void btnBack(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancAddPayment, "/view/payment/MainPayment.fxml");
    }

    public void btnSave(ActionEvent actionEvent) {
    }

    public void btnCancel(ActionEvent actionEvent) {
    }
}
