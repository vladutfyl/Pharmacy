package ro.utcluj;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class AppStart extends Application{

        public static void main(String[] args) {
            launch(args);
        }

        public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 650, 450));
            stage.show();
        }
    }


