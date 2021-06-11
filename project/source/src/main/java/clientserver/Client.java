package clientserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entity.Drug;
import model.entity.User;
import view.LoginView;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class Client extends Application
{
    public void start(Stage stage) throws Exception {

        Client.Connection connection = new Connection(new Socket("localhost", 5002));
        connection.start();
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/LoginPage.fxml"));
        Parent root = fxmlLoader.load();

        LoginView loginView=fxmlLoader.getController();
        loginView.setConnection(connection);

        stage.setTitle("Login");
        stage.setScene(new Scene(root, 650, 450));
        stage.show();
    }

    public static class Connection extends Thread
    {
        private final Socket socket;
        private final ObjectOutputStream output;
        private final ObjectInputStream input;

        public Connection(Socket socket) throws IOException
        {
            this.socket = socket;
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            
        }

        @Override
        public void run()
        {
                while (socket.isConnected())
                {

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
        }

        public boolean checkUser(String username, String password) throws IOException {

            output.writeObject("checkUser");
            output.writeObject(username);
            output.writeObject(password);
            output.flush();

            return input.readBoolean();
        }

        public User getUser(String username) throws IOException, ClassNotFoundException {
            output.writeObject("getUser");
            output.writeObject(username);
            output.flush();

            return (User) input.readObject();
        }

        public void addDrug(String drugName, String type, float price, int quantity) throws IOException {
            output.writeObject("addDrug");
            output.writeObject(drugName);
            output.writeObject(type);
            output.writeFloat(price);
            output.writeInt(quantity);
            output.flush();
        }

        public List<User> getUsers() throws IOException, ClassNotFoundException {
            output.writeObject("getUsers");
            output.flush();

            return (List<User>) input.readObject();
        }

        public  List<Drug> getMeds() throws IOException, ClassNotFoundException {
            output.writeObject("getMeds");
            output.flush();

            return (List<Drug>) input.readObject();
        }

        public void deleteUser(User user) throws IOException {
            output.writeObject("deleteUser");
            output.writeObject(user);
            output.flush();
        }

        public  void deleteDrug(String name) throws IOException {
            output.writeObject("deleteDrug");
            output.writeObject(name);
            output.flush();
        }

        public void modifyDrug(String name, float price, int quantity, float rating, int discount, int quantityDiscounted) throws IOException {
            output.writeObject("modifyDrug");
            output.writeObject(name);
            output.writeFloat(price);
            output.writeInt(quantity);
            output.writeFloat(rating);
            output.writeInt(discount);
            output.writeInt(quantityDiscounted);
            output.flush();
        }

        public void addUser(String username, String password, String email, String name) throws IOException {
            output.writeObject("addUser");
            output.writeObject(username);
            output.writeObject(password);
            output.writeObject(email);
            output.writeObject(name);
            output.flush();
        }

        public void modifyUser(String username, String email, String password, String name, float money) throws IOException {
            output.writeObject("modifyUser");
            output.writeObject(username);
            output.writeObject(email);
            output.writeObject(password);
            output.writeObject(name);
            output.writeFloat(money);
            output.flush();
        }

        public Drug getMed(String name) throws IOException, ClassNotFoundException {
            output.writeObject("getMed");
            output.writeObject(name);
            output.flush();
            return (Drug)input.readObject();
        }

        public void updateQuantity(Drug d, int i) throws IOException {
            output.writeObject("updateQuantity");
            output.writeObject(d);
            output.writeInt(i);
            output.flush();
        }

        public void addBoughtDrug(String nume, String type, float price, int quantity,User user) throws IOException {
            output.writeObject("addBoughtDrug");
            output.writeObject(user);
            output.writeObject(nume);
            output.writeObject(type);
            output.writeFloat(price);
            output.writeInt(quantity);
            output.flush();
        }

        public void addShoppingCart(String nume, String type, float price, int quantity, User user) throws IOException {
            output.writeObject("addShoppingCart");
            output.writeObject(user);
            output.writeObject(nume);
            output.writeObject(type);
            output.writeFloat(price);
            output.writeInt(quantity);
            output.flush();
        }

        public void addRating(String name, float currentRating) throws IOException {
            output.writeObject("addRating");
            output.writeObject(name);
            output.writeFloat(currentRating);
            output.flush();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        launch(args);
    }
}
