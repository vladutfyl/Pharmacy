package view;

import clientserver.Client;
import controller.MainPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dto.DrugDto;
import model.dto.ShoppingCartDto;
import model.entity.ShoppingCart;
import model.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class MainPageView implements Initializable,IMainPageView {
    @FXML
    public TableView shoppingCartList;
    @FXML
    public TableView productsList;
    @FXML
    public Button loginButton;
    @FXML
    public TextField quantityTextField;
    @FXML
    public Button addButton;
    @FXML
    public Button continueButton;
    @FXML
    public Button deleteButton;
    public Button ratingButton;
    public TextField ratingTextField;

    public static float actualDiscountedAmount=0;
    public static float maxDiscountedAmount=100;
    public Label labelMaxAmount;

    MainPageController mainController;
    Client.Connection connection;

    private User user;

    public void setActualDiscountedAmount(float actualDiscountedAmount) {
        MainPageView.actualDiscountedAmount=actualDiscountedAmount;
        labelMaxAmount.setText(actualDiscountedAmount+"/"+maxDiscountedAmount);
    }

    public void setMaxDiscount(float maxDiscountedAmount) {
        MainPageView.maxDiscountedAmount=maxDiscountedAmount;
        labelMaxAmount.setText(actualDiscountedAmount+"/"+maxDiscountedAmount);
    }


    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        Parent root = loader.load();

        LoginView loginView=loader.getController();
        loginView.setConnection(connection);
        LoginView.actualDiscountedAmount=actualDiscountedAmount;
        LoginView.maxDiscountedAmount=maxDiscountedAmount;

        stage.setTitle("OnLine-Pharma Login Page");
        stage.setScene(new Scene(root, 650, 450));
        stage.show();
    }

    public void addToCart(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        DrugDto drug=(DrugDto) productsList.getSelectionModel().getSelectedItem();
        mainController.addToCart(drug);
        mainController.update();
    }

    public void continueOrder(ActionEvent event) throws IOException, ClassNotFoundException {
        mainController.buy();
        mainController.update();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainController = new MainPageController(this);
        labelMaxAmount.setText(actualDiscountedAmount+"/"+maxDiscountedAmount);
    }

    @Override
    public String getQuantity() {
        return quantityTextField.getText();
    }

    @Override
    public Client.Connection getConnection() {
        return connection;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setInfo(String msg) {
        quantityTextField.setText(msg);
    }

    @Override
    public TableView<DrugDto> getDrugTable() {
        return productsList;
    }

    @Override
    public TableView<ShoppingCartDto> getShoppingCartTable() {
        return shoppingCartList;
    }

    @Override
    public Set<ShoppingCart> getShoppingCart() {
        return user.getShoppingCart();
    }

    public void setConnection(Client.Connection connection) {
        this.connection=connection;
        try {
            mainController.update();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void setUser(User user) {
        this.user=user;
    }

    @Override
    public String getRating() {
        return ratingTextField.getText();
    }

    public void rate(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        DrugDto drug=(DrugDto) productsList.getSelectionModel().getSelectedItem();
        mainController.addRating(drug);
        mainController.update();
    }

}
