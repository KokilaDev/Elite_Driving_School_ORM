package lk.ijse.elite_driving_school_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.elite_driving_school_orm.dto.Roles;
import lk.ijse.elite_driving_school_orm.dto.UserDTO;
import lk.ijse.elite_driving_school_orm.util.AuthUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public AnchorPane ancLogin;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public ImageView image;

    private final List<UserDTO> userDTOs = new ArrayList<>();

    public void btnLogin(ActionEvent actionEvent) throws Exception {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        for (UserDTO userDTO : userDTOs) {
            if (userDTO.getUsername().equals(username) && userDTO.getPassword().equals(password)) {
                Roles role = userDTO.getRole();
                AuthUtil.setUserDTO(userDTO);

                String fxmlPath;
                if (role.equals(Roles.ADMIN)) {
                    fxmlPath = "/view/dashboard/ADashBoard.fxml";
                } else {
                    fxmlPath = "/view/dashboard/RDashboard.fxml";
                }

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle(role.equals(Roles.ADMIN) ? "Admin Dashboard" : "User Dashboard");
                stage.show();

                Stage window = (Stage) txtPassword.getScene().getWindow();
                window.close();
                break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Admin");
        userDTO.setPassword("4321");
        userDTO.setRole(Roles.ADMIN);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setUsername("User");
        userDTO2.setPassword("1234");
        userDTO2.setRole(Roles.RECEPTIONIST);

        userDTOs.add(userDTO);
        userDTOs.add(userDTO2);
    }
}
