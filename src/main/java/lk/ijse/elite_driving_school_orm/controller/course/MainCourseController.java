package lk.ijse.elite_driving_school_orm.controller.course;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elite_driving_school_orm.bo.BOFactory;
import lk.ijse.elite_driving_school_orm.bo.BOTypes;
import lk.ijse.elite_driving_school_orm.bo.custom.CourseBO;
import lk.ijse.elite_driving_school_orm.bo.exception.InUseException;
import lk.ijse.elite_driving_school_orm.dto.tm.CourseTM;
import lk.ijse.elite_driving_school_orm.util.AuthUtil;
import lk.ijse.elite_driving_school_orm.util.NavigationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainCourseController implements Initializable {

    public AnchorPane ancCourse;
    public Button btnAddNewCourse;

    public TableView<CourseTM> tblCourse;
    public TableColumn<CourseTM, String> colCourseID;
    public TableColumn<CourseTM, String> colName;
    public TableColumn<CourseTM, Integer> colFee;
    public TableColumn<CourseTM, Double> colDuration;

    public Button btnUpdate;
    public Button btnDelete;

    private CourseTM selectedCourse;

    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOTypes.COURSE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        try {
            resetPage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }

        boolean isAdmin = AuthUtil.isAdmin();
        if (!isAdmin) {
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
        }

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        tblCourse.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
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

        btnAddNewCourse.setDisable(false);
    }

    public void loadTableData() throws SQLException {
        tblCourse.setItems(FXCollections.observableArrayList(
                courseBO.getAllCourses().stream().map(courseDTO ->
                        new CourseTM(
                                courseDTO.getCourseId(),
                                courseDTO.getName(),
                                courseDTO.getDuration(),
                                courseDTO.getFee()
                        )).toList()
        ));
        tblCourse.refresh();
    }

    public void btnAddNewCourse(ActionEvent actionEvent) {
        NavigationUtil.navigateTo(ancCourse, "/view/course/AddCourse.fxml");
    }

    public void btnUpdate(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/course/UpdateCourse.fxml"));
            Parent root = loader.load();

            UpdateCourseController controller = loader.getController();

            controller.setCourseData(selectedCourse);

            ancCourse.getChildren().clear();
            ancCourse.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnDelete(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            try {
                boolean isDeleted = courseBO.deleteCourse(selectedCourse.getCourseId());
                resetPage();
                new Alert(
                        Alert.AlertType.INFORMATION, "Course deleted successfully"
                ).show();
            } catch (InUseException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Course not deleted").show();
            }
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        selectedCourse = tblCourse.getSelectionModel().getSelectedItem();

        if (selectedCourse != null) {
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
