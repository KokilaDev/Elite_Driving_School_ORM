package lk.ijse.elite_driving_school_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
        navigateTo("/view/DashboardContent.fxml");
    }

    public void btnDashboard(ActionEvent actionEvent) {
        navigateTo("/view/DashboardContent.fxml");
    }
    
    public void btnStudents(ActionEvent actionEvent) {
        navigateTo("/view/Student.fxml");
    }

    public void btnLessons(ActionEvent actionEvent) {
        navigateTo("/view/Lesson.fxml");
    }

    public void btnPayments(ActionEvent actionEvent) {
        navigateTo("/view/Payment.fxml");
    }

    private void navigateTo(String path) {
        try {
            ancRDashBoards.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancRDashBoards.widthProperty());
            anchorPane.prefHeightProperty().bind(ancRDashBoards.heightProperty());

            ancRDashBoards.getChildren().add(anchorPane);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        ancRDashBoard.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
        ancRDashBoard.prefWidthProperty().bind(ancRDashBoard.widthProperty());
        ancRDashBoard.prefHeightProperty().bind(ancRDashBoard.heightProperty());
        ancRDashBoard.getChildren().add(pane);
    }
}
