package lk.ijse.elite_driving_school_orm.controller.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RDashBoardController implements Initializable {
    public AnchorPane ancRDashBoard;
    public AnchorPane ancRDashBoards;
    public Button btnStudents;
    public Button btnLessons;
    public Button btnPayments;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NavigationUtil.navigateTo(ancRDashBoards, "/view/dashboard/DashboardContent.fxml");
    }

    public void btnDashboard(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancRDashBoards, "/view/dashboard/DashboardContent.fxml");
    }
    
    public void btnStudents(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancRDashBoards, "/view/student/MainStudent.fxml");
    }

    public void btnLessons(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancRDashBoards, "/view/lesson/MainLesson.fxml");
    }

    public void btnPayments(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancRDashBoards, "/view/payment/MainPayment.fxml");
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        ancRDashBoard.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
        ancRDashBoard.prefWidthProperty().bind(ancRDashBoard.widthProperty());
        ancRDashBoard.prefHeightProperty().bind(ancRDashBoard.heightProperty());
        ancRDashBoard.getChildren().add(pane);
    }
}
