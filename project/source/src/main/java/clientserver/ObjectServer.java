package clientserver;

import model.entity.Drug;
import model.entity.User;
import utils.ServicesClassUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {

    public static void main(String[] argv) throws IOException, ClassNotFoundException {
        ServerSocket s = new ServerSocket(5002);

        while (true) {
            Socket t = s.accept();// wait for client to connect
            ServerThread sv=new ServerThread(t);
            sv.start();
        }
    }
}
class ServerThread extends Thread{

    Socket s;
    ObjectInputStream inputStream=null;
    ObjectOutputStream outputStream=null;

    public ServerThread(Socket s){
        this.s=s;
    }

    public synchronized void run(){
        try {
            inputStream = new ObjectInputStream(s.getInputStream());
            outputStream = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            while(true) {
            String commandReceived = (String) inputStream.readObject();
            this.switchCommands(commandReceived);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(inputStream!=null) {
                        inputStream.close();
                    }
                if(outputStream!=null) {
                    outputStream.close();
                }
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void switchCommands(String commandReceived) throws IOException, ClassNotFoundException {
        switch(commandReceived){
            case "checkUser":
                checkUser();
                break;
            case "getUser":
                getUser();
                break;
            case "getUsers":
                outputStream.writeObject(ServicesClassUtils.userService.getUsers());
                outputStream.flush();
                break;
            case "getMeds":
                outputStream.writeObject(ServicesClassUtils.drugService.getMeds());
                outputStream.flush();
                break;
            case "deleteUser":
                User u=(User)inputStream.readObject();
                ServicesClassUtils.userService.deleteUser(u);
                break;
            case "deleteDrug":
                String name=(String) inputStream.readObject();
                ServicesClassUtils.drugService.deleteDrug(name);
                break;
            case "addDrug":
                addDrug();
                break;
            case "modifyDrug":
                modifyDrug();
                break;
            case "addUser":
                addUser();
                break;
            case "modifyUser":
                modifyUser();
                break;
            case "getMed":
                getMed();
                break;
            case "updateQuantity":
                updateQuantity();
                break;
            case "addBoughtDrug":
                addBoughtDrug();
                break;
            case "addShoppingCart":
                addShoppingCart();
                break;
            case "addRating":
                addRating();
                break;
            default:
                break;
        }
    }

    private void addRating() throws IOException, ClassNotFoundException {
        String drugName=(String)inputStream.readObject();
        float currentRating=inputStream.readFloat();
        ServicesClassUtils.drugService.updateRating(drugName,currentRating);
    }

    private void addShoppingCart() throws IOException, ClassNotFoundException {
        User u=(User) inputStream.readObject();
        String name=(String) inputStream.readObject();
        String type=(String) inputStream.readObject();
        float price= inputStream.readFloat();
        int quantity=inputStream.readInt();
        ServicesClassUtils.shoppingCartService.add(name,type,price,quantity,u);
    }


    private void addBoughtDrug() throws IOException, ClassNotFoundException {
        User u=(User) inputStream.readObject();
        String name=(String) inputStream.readObject();
        String type=(String) inputStream.readObject();
        float price= inputStream.readFloat();
        int quantity=inputStream.readInt();
        ServicesClassUtils.boughtDrugService.add(name,type,price,quantity,u);
    }



    private void updateQuantity() throws IOException, ClassNotFoundException {
        Drug d=(Drug) inputStream.readObject();
        int quantity= inputStream.readInt();
        ServicesClassUtils.drugService.updateQuantity(d,quantity);
    }

    private void getMed() throws IOException, ClassNotFoundException {
        String name=(String) inputStream.readObject();
        Drug d= ServicesClassUtils.drugService.getMed(name);
        outputStream.writeObject(d);
    }

    private void modifyDrug() throws IOException, ClassNotFoundException {
        String name=(String) inputStream.readObject();
        float price=inputStream.readFloat();
        int quantity=inputStream.readInt();
        float rating=inputStream.readFloat();
        int discount=inputStream.readInt();
        int qunatityDiscounted=inputStream.readInt();
        ServicesClassUtils.drugService.modifyDrug(name,price,quantity,rating,discount, qunatityDiscounted);
    }

    private void addDrug() throws IOException, ClassNotFoundException {
        String name=(String) inputStream.readObject();
        String type=(String) inputStream.readObject();
        float price=inputStream.readFloat();
        int quantity=inputStream.readInt();
        ServicesClassUtils.drugService.addDrug(name,type,price,quantity);
    }

    private void getUser() throws IOException, ClassNotFoundException {
        String username=(String) inputStream.readObject();
        outputStream.writeObject(ServicesClassUtils.userService.getCurrentUser(username));
        outputStream.flush();
    }

    private void checkUser() throws IOException, ClassNotFoundException {
        String username=(String) inputStream.readObject();
        String password=(String) inputStream.readObject();
        outputStream.writeBoolean(ServicesClassUtils.userService.checkUser(username,password));
        outputStream.flush();
    }

    private void addUser() throws IOException, ClassNotFoundException {
        String username=(String) inputStream.readObject();
        String password=(String) inputStream.readObject();
        String email=(String) inputStream.readObject();
        String name=(String) inputStream.readObject();
        ServicesClassUtils.userService.addUser(username,password,name,email);
    }

    private void modifyUser() throws IOException, ClassNotFoundException {
        String username=(String) inputStream.readObject();
        String email=(String) inputStream.readObject();
        String password=(String) inputStream.readObject();
        String name=(String) inputStream.readObject();
        float money= inputStream.readFloat();
        ServicesClassUtils.userService.modifyUser(username,email,password,name,money);
    }

}