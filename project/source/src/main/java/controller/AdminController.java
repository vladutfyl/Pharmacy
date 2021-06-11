package controller;

import clientserver.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.DrugDto;
import model.dto.UserDto;
import model.entity.Drug;
import model.entity.User;
import utils.validators.DrugValidatorUtils;
import utils.report.ReportFactory;
import view.IAdminView;

import java.io.IOException;

public class AdminController {
    IAdminView iAdminView;

    public AdminController(IAdminView iAdminView) {
        this.iAdminView=iAdminView;
    }

    public boolean addMed() throws IOException {
        String drugName=iAdminView.getDrugName();
        float price=Float.parseFloat(iAdminView.getPrice());
        int quantity=Integer.parseInt(iAdminView.getQuantity());
        String type=iAdminView.getType();
        Client.Connection conn=iAdminView.getConnection();
        if(DrugValidatorUtils.validateMeds(iAdminView,drugName,price,quantity)) {
            conn.addDrug(drugName, type, price, quantity);
            return true;
        }
        return false;
    }

    public void update() throws IOException, ClassNotFoundException {
        ObservableList<UserDto> users=FXCollections.observableArrayList();
        ObservableList<DrugDto> drugs= FXCollections.observableArrayList();
        Client.Connection conn=iAdminView.getConnection();
        for(Drug d:conn.getMeds()){
            drugs.add(new DrugDto(d.getName(),d.getType(),d.getPrice(),d.getStock(),d.getRating(), d.getDiscount(), d.getQuantityDiscounted()));
        }
        for(User u:conn.getUsers()){
            users.add(new UserDto(u.getName(),u.getUsername(),u.getPassword(),u.getEmail(),Float.toString(u.getMoney())));
        }

        iAdminView.getUserTable().setItems(users);
        iAdminView.getDrugTable().setItems(drugs);
    }

    public void deleteUser(UserDto u) throws IOException, ClassNotFoundException {
        String username=u.getUsername();
        Client.Connection conn=iAdminView.getConnection();
        User user=conn.getUser(username);
        conn.deleteUser(user);
        iAdminView.setInfo("User "+u.getUsername()+" deleted successfully");
    }

    public void deleteDrug(DrugDto drug) throws IOException {
        String name=drug.getName();
        Client.Connection conn=iAdminView.getConnection();
        conn.deleteDrug(name);
        iAdminView.setInfo("Drug "+drug.getName()+" deleted successfully");
    }

    public void modifyProduct(DrugDto drug) throws IOException {
        String name=drug.getName();
        float price=Float.parseFloat(iAdminView.getPrice());
        int quantity=Integer.parseInt(iAdminView.getQuantity());
        float rating=Float.parseFloat(iAdminView.getRating());
        int discount=Integer.parseInt(iAdminView.getDiscount());
        int quantityDiscounted=Integer.parseInt(iAdminView.getQuantityDiscounted());
        if(discount<0 || discount>100){
            iAdminView.setInfo("Discount must be in 0-100 interval");
        }
        else {
            Client.Connection conn=iAdminView.getConnection();
            if(DrugValidatorUtils.validateMeds(iAdminView,name,price,quantity)) {
                conn.modifyDrug(name, price, quantity, rating, discount, quantityDiscounted);
            }
            iAdminView.setInfo("Drug modified with success");
        }
    }

    public void modifyUser(UserDto user) throws IOException {
        String username=user.getUsername();
        String email=iAdminView.getEmail();
        String password=iAdminView.getPassword();
        String name= iAdminView.getNume();
        float money=Float.parseFloat(iAdminView.getUsername());
        Client.Connection conn=iAdminView.getConnection();
        conn.modifyUser(username,email,password,name,money);
        iAdminView.setInfo("User modified with success");
    }

    public void generateReport(UserDto u, ReportFactory f) throws IOException, ClassNotFoundException {
        Client.Connection conn=iAdminView.getConnection();
        User currentUser=conn.getUser(u.getUsername());

        f.saveReport(currentUser.getBoughtDrugs());
    }

}
