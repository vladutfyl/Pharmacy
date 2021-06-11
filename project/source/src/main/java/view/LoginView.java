package view;


import clientserver.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import controller.LoginController;
import model.entity.User;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements Initializable, ILoginView {
    LoginController loginController;

    public static float actualDiscountedAmount=0;
    public static float maxDiscountedAmount=100;

    Client.Connection connection;
    @FXML
    private ImageView logo;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button goToSignUp;

    @FXML
    public void loginButtonOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        loginController.login();
    }

    private User user;

    @FXML
    public void goToSignUpOnAction(ActionEvent event) throws IOException{
        Stage stage = (Stage) goToSignUp.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegisterPage.fxml"));
        Parent root = loader.load();

        RegisterView registerView=loader.getController();
        registerView.setConnection(connection);

        stage.setTitle("OnLine-Pharma Register Page");
        stage.setScene(new Scene(root, 650, 450));
        stage.show();
    }

    public void setInfo(String s){
        usernameTextField.setText(s);
    }
    

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginController = new LoginController(this);
    }


    @Override
    public String getUsername() {
        return usernameTextField.getText();
    }

    @Override
    public String getPassword() {
        return passwordTextField.getText();
    }

    @Override
    public void showAdminView() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminPage.fxml"));
        Parent root = loader.load();

        AdminView adminView=loader.getController();
        adminView.setConnection(connection);
        adminView.setMaxDiscountedAmount(maxDiscountedAmount);
        adminView.setActualDiscountedAmount(actualDiscountedAmount);

        stage.setTitle("OnLine-Pharma Admin Page");
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void showErrorMessage(String message) throws IOException {
        usernameTextField.setText(message);
    }

    @Override
    public void showMainView() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainPage.fxml"));
        Parent root = loader.load();

        MainPageView mainPageView=loader.getController();
        mainPageView.setMaxDiscount(maxDiscountedAmount);
        mainPageView.setActualDiscountedAmount(actualDiscountedAmount);
        mainPageView.setConnection(connection);
        mainPageView.setUser(user);


        stage.setTitle("OnLine-Pharma Main Page");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public User getUser() {
        return user;
    }

    @Override
    public Client.Connection getConnection() {
        return connection;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setConnection(Client.Connection connection) {
        this.connection=connection;
    }

}
