package view;

import clientserver.Client;
import controller.RegisterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterView implements Initializable, IRegisterView {

    @FXML
    private Button goToLogin;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button signupButton;

    private RegisterController registerController;
    private Client.Connection connection;

    public void registerAccount(ActionEvent actionEvent) throws IOException {
        registerController.register();
    }

    public void goToLoginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goToLogin.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        Parent root = loader.load();

        LoginView loginView=loader.getController();
        loginView.setConnection(connection);

        stage.setTitle("OnLine-Pharma Login Page");
        stage.setScene(new Scene(root, 650, 450));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerController=new RegisterController(this);
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
    public String getEmail() {
        return emailTextField.getText();
    }

    @Override
    public String getName() {
        return nameTextField.getText();
    }

    @Override
    public Client.Connection getConnection() {
        return connection;
    }

    @Override
    public void setInfo(String message) {
        usernameTextField.setText(message);
    }

    @Override
    public void resetFields() {
        nameTextField.setText("");
        usernameTextField.setText("");
        emailTextField.setText("");
        passwordTextField.setText("");
    }

    public void setConnection(Client.Connection connection) {
        this.connection=connection;
    }
}
