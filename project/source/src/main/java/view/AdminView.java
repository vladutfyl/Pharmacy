package view;


import clientserver.Client;
import controller.AdminController;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import model.dto.DrugDto;
import model.dto.UserDto;
import utils.report.PDFReport;
import utils.report.ReportFactory;
import utils.report.TXTReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminView implements Initializable, IAdminView{

    public TextField ratingTextField;
    public TextField quantityDiscountedTextField;
    public Label labelMaxAmount;
    public Button setMaxDiscount;
    public TextField maxDiscountTextField;
    Client.Connection connection;

    @FXML
    private ImageView logo;

    @FXML
    private TableView<DrugDto> productsList;

    @FXML
    private TableView<UserDto> usersList;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button modifyUserButton;

    @FXML
    private TextField nameProductTextField;

    @FXML
    private TextField typeTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField discountTextField;

    @FXML
    private Button refreshButton;

    @FXML
    private Button backToLoginButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField nameUserTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    private AdminController adminController;

    public static float actualDiscountedAmount=0;
    public static float maxDiscountedAmount=100;



    @FXML
    void generateReport(ActionEvent event) throws IOException, ClassNotFoundException {
        UserDto u=(UserDto) usersList.getSelectionModel().getSelectedItem();
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterTxt = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilterPdf = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilterTxt);
        fileChooser.getExtensionFilters().add(extFilterPdf);
        File file = fileChooser.showSaveDialog(refreshButton.getScene().getWindow());
        if(file!=null) {
            ReportFactory reportFactory;
            if(file.getCanonicalPath().endsWith(".pdf")){
                reportFactory=new PDFReport();
            }else{
                reportFactory=new TXTReport();
            }
            reportFactory.setFile(file);
            adminController.generateReport(u, reportFactory);
        }
    }

    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) backToLoginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        Parent root = loader.load();

        LoginView loginView=loader.getController();
        loginView.setConnection(connection);
        LoginView.actualDiscountedAmount=actualDiscountedAmount;
        LoginView.maxDiscountedAmount=maxDiscountedAmount;

        stage.setTitle("On_line-Pharma Login");
        stage.setScene(new Scene(root, 650, 450));
        stage.show();
    }



    @FXML
    void deleteProduct(ActionEvent event) throws IOException {
        DrugDto drug=(DrugDto)productsList.getSelectionModel().getSelectedItem();
        adminController.deleteDrug(drug);
        productsList.getItems().remove(drug);
    }

    @FXML
    void deleteUser(ActionEvent event) throws IOException, ClassNotFoundException {
        UserDto u=(UserDto) usersList.getSelectionModel().getSelectedItem();
        adminController.deleteUser(u);
        usersList.getItems().remove(u);
    }

    @FXML
    void modifyProduct(ActionEvent event) throws IOException, ClassNotFoundException {
        DrugDto drug=(DrugDto) productsList.getSelectionModel().getSelectedItem();
        adminController.modifyProduct(drug);
        adminController.update();
    }

    @FXML
    void modifyUser(ActionEvent event) throws IOException, ClassNotFoundException {
        UserDto user=(UserDto) usersList.getSelectionModel().getSelectedItem();
        adminController.modifyUser(user);
        adminController.update();
    }

    @FXML
    void addProducts(ActionEvent event) throws IOException, ClassNotFoundException {
        if(adminController.addMed()) {
            adminController.update();
        }
    }


    public void setInfo(String s){
        nameProductTextField.setText(s);
    }

    @Override
    public String getDiscount() {
        return discountTextField.getText();
    }

    @Override
    public String getRating() {
        return ratingTextField.getText();
    }

    @Override
    public String getQuantityDiscounted() {
        return quantityDiscountedTextField.getText();
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("images/caduceus.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        logo.setImage(brandingImage);
        adminController=new AdminController(this);
        labelMaxAmount.setText(actualDiscountedAmount+"/"+maxDiscountedAmount);
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
    public String getNume() {
        return nameUserTextField.getText();
    }

    @Override
    public String getEmail() {
        return emailTextField.getText();
    }

    @Override
    public String getDrugName() {
        return nameProductTextField.getText();
    }

    @Override
    public String getType() {
        return typeTextField.getText();
    }

    @Override
    public String getPrice() {
        return priceTextField.getText();
    }

    @Override
    public String getQuantity() {
        return quantityTextField.getText();
    }

    @Override
    public TableView getUserTable() {
        return usersList;
    }

    @Override
    public TableView getDrugTable() {
        return productsList;
    }

    @Override
    public Client.Connection getConnection() {
        return connection;
    }


    public void setConnection(Client.Connection connection) {
        this.connection=connection;
        try {
            adminController.update();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setMaxDiscount(ActionEvent actionEvent) {
        maxDiscountedAmount=Float.parseFloat(maxDiscountTextField.getText());
        labelMaxAmount.setText(actualDiscountedAmount+"/"+maxDiscountedAmount);
    }

    public void setMaxDiscountedAmount(float maxDiscountedAmount) {
        AdminView.maxDiscountedAmount=maxDiscountedAmount;
        labelMaxAmount.setText(actualDiscountedAmount+"/"+maxDiscountedAmount);
    }

    public void setActualDiscountedAmount(float actualDiscountedAmount) {
        AdminView.actualDiscountedAmount=actualDiscountedAmount;
        labelMaxAmount.setText(actualDiscountedAmount+"/"+maxDiscountedAmount);
    }
}

