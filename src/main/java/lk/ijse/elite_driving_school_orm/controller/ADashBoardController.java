package lk.ijse.elite_driving_school_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ADashBoardController implements Initializable {
    public AnchorPane ancADashBoard;
    public AnchorPane ancADashBoards;
    public Button btnStudents;
    public Button btnCourses;
    public Button btnInstructors;
    public Button btnLessons;
    public Button btnPayments;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/DashBoardContent.fxml");
    }

    public void btnDashboard(ActionEvent actionEvent) {
        navigateTo("/view/DashBoardContent.fxml");
    }

    public void btnStudents(ActionEvent actionEvent) {
        navigateTo("/view/Student.fxml");
    }

    public void btnCourses(ActionEvent actionEvent) {
        navigateTo("/view/Course.fxml");
    }

    public void btnInstructors(ActionEvent actionEvent) {
        navigateTo("/view/Instructor.fxml");
    }

    public void btnLessons(ActionEvent actionEvent) {
        navigateTo("/view/Lesson.fxml");
    }

    public void btnPayments(ActionEvent actionEvent) {
        navigateTo("/view/Payment.fxml");
    }

    private void navigateTo(String path) {
        try {
            ancADashBoards.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancADashBoards.widthProperty());
            anchorPane.prefHeightProperty().bind(ancADashBoards.heightProperty());

            ancADashBoards.getChildren().add(anchorPane);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        ancADashBoard.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
        pane.prefWidthProperty().bind(ancADashBoard.widthProperty());
        pane.prefHeightProperty().bind(ancADashBoard.heightProperty());
        ancADashBoard.getChildren().add(pane);
    }
    
}
