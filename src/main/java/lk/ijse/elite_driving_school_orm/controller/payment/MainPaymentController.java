package lk.ijse.elite_driving_school_orm.controller.payment;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.PaymentBO;
import lk.ijse.elite_driving_school_orm.dto.tm.PaymentTM;
import lk.ijse.elite_driving_school_orm.util.AuthUtil;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainPaymentController implements Initializable {
    public AnchorPane ancPayment;
    public Button btnAddNewPayment;
    public TableView<PaymentTM> tblPayment;
    public TableColumn<PaymentTM, String> colPaymentID;
    public TableColumn<PaymentTM, String> colStudentID;
    public TableColumn<PaymentTM, String> colTotalAmount;
    public TableColumn<PaymentTM, String> colDate;
    public TableColumn<PaymentTM, String> colStatus;
    public Button btnUpdate;
    public Button btnDelete;

    private final PaymentBO paymentBO = BOFactory.getInstance().getBO(BOTypes.PAYMENT);
    private PaymentTM selectedPayment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        try {
            resetPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }

        boolean isAdmin = AuthUtil.isAdmin();
        if (!isAdmin) {
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
        }

        // disable update/delete until a row is selected
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        tblPayment.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            } else {
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        });
    }

    private void resetPage() throws SQLException {
        loadTableData();

//        btnUpdate.setDisable(false);
//        btnDelete.setDisable(false);

        btnAddNewPayment.setDisable(false);
    }

    private void loadTableData() throws SQLException {
        tblPayment.setItems(FXCollections.observableArrayList(
                paymentBO.getAllPayment().stream().map(paymentDTO ->
                        new PaymentTM(
                                paymentDTO.getPaymentId(),
                                paymentDTO.getAmount(),
                                paymentDTO.getDate(),
                                paymentDTO.getStatus(),
                                paymentDTO.getStudentId()
                        )).toList()
        ));
        tblPayment.refresh();
    }

    public void btnAddNewPayment(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancPayment, "/view/payment/AddPayment.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancPayment, "/view/payment/UpdatePayment.fxml");
    }

    public void btnDelete(ActionEvent actionEvent) {

    }

    public void onClickTable(javafx.scene.input.MouseEvent mouseEvent) {
    }
}
