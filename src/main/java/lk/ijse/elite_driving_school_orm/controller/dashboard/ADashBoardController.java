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
        NavigationUtil.navigateTo(ancADashBoards,"/view/dashboard/DashBoardContent.fxml");
    }

    public void btnDashboard(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancADashBoards,"/view/dashboard/DashBoardContent.fxml");
    }

    public void btnStudents(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancADashBoards,"/view/student/MainStudent.fxml");
    }

    public void btnCourses(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancADashBoards,"/view/course/MainCourse.fxml");
    }

    public void btnInstructors(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancADashBoards,"/view/instructor/MainInstructor.fxml");
    }

    public void btnLessons(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancADashBoards,"/view/lesson/MainLesson.fxml");
    }

    public void btnPayments(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancADashBoards,"/view/payment/MainPayment.fxml");
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        ancADashBoard.getChildren().clear();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
        pane.prefWidthProperty().bind(ancADashBoard.widthProperty());
        pane.prefHeightProperty().bind(ancADashBoard.heightProperty());
        ancADashBoard.getChildren().add(pane);
    }
    
}
