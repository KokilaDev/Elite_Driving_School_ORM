package lk.ijse.elite_driving_school_orm.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NavigationUtil {
    public static void navigateTo(AnchorPane container, String fxmlPath) {
        try {
            container.getChildren().clear();

            AnchorPane loadedPane = FXMLLoader.load(NavigationUtil.class.getResource(fxmlPath));

            loadedPane.prefWidthProperty().bind(container.widthProperty());
            loadedPane.prefHeightProperty().bind(container.heightProperty());

            container.getChildren().add(loadedPane);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }
}
