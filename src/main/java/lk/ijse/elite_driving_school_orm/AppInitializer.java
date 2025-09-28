package lk.ijse.elite_driving_school_orm;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(
                new FXMLLoader(getClass().getResource("/view/LoadingScreen.fxml")).load()
        ));
        stage.show();

        Task<Scene> loadingTask = new Task<>() {
            @Override
            protected Scene call() throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashboard/ADashBoard.fxml"));
                return new Scene(fxmlLoader.load());
            }
        };

        loadingTask.setOnSucceeded(event -> {
            Scene value = loadingTask.getValue();

            stage.setTitle("Dashboard");
            stage.setScene(value);
        });

        loadingTask.setOnFailed(event -> {
            System.out.println("Failed to load application");
        });

        new Thread(loadingTask).start();
    }
}