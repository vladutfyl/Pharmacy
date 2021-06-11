package controller;

import clientserver.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.DrugDto;


import model.dto.ShoppingCartDto;
import model.entity.Drug;
import model.entity.ShoppingCart;
import model.entity.User;
import view.IMainPageView;
import view.MainPageView;

import java.io.IOException;

public class MainPageController {
    IMainPageView iMainPageView;

    public MainPageController(IMainPageView mainPageView) {
        iMainPageView=mainPageView;
    }

    public void buy() throws IOException{
        for(ShoppingCart s:iMainPageView.getShoppingCart()){
            iMainPageView.getConnection().addBoughtDrug(s.getName(),s.getType(),s.getPrice(),s.getCantity(),s.getUser());
        }
        iMainPageView.getShoppingCart().clear();
        iMainPageView.setInfo("Bought Successfully");
    }

    public void update() throws IOException, ClassNotFoundException {
        ObservableList<ShoppingCartDto> shoppingCartDtos=FXCollections.observableArrayList();
        ObservableList<DrugDto> drugs= FXCollections.observableArrayList();
        Client.Connection conn=iMainPageView.getConnection();
        for(Drug d:conn.getMeds()){
            drugs.add(new DrugDto(d.getName(),d.getType(),d.getPrice(),d.getStock(),d.getRating(),d.getDiscount(),d.getQuantityDiscounted()));
        }
        if(iMainPageView.getUser()!=null)
        {
            for(ShoppingCart s:iMainPageView.getShoppingCart()){
                shoppingCartDtos.add(new ShoppingCartDto(s.getName(),s.getType(),s.getPrice(),s.getCantity()));
            }
        }
        iMainPageView.getShoppingCartTable().setItems(shoppingCartDtos);
        iMainPageView.getDrugTable().setItems(drugs);
    }

    public void addToCart(DrugDto drug) throws IOException, ClassNotFoundException {
        int quantity=Integer.parseInt(iMainPageView.getQuantity());
        String nume=drug.getName();
        float price=Float.parseFloat(drug.getPrice());
        String type=drug.getType();
        User user=iMainPageView.getUser();
        Drug d=iMainPageView.getConnection().getMed(nume);
        float priceDiscounted=0;
        if(d.getStock()<quantity) {
            iMainPageView.setInfo("Not enough stocks");
        }
        else {
            if(quantity>=Integer.parseInt(drug.getQuantityDiscounted())){
                priceDiscounted=price*Integer.parseInt(drug.getDiscount())/100;
                price=price-priceDiscounted;
            }
            if(user.getMoney()<quantity*price){
                iMainPageView.setInfo("Insufficient funds");
            } else {
               iMainPageView.getConnection().addShoppingCart(nume,type,price,quantity,user);
               iMainPageView.setInfo("Added successfully");
               iMainPageView.getConnection().updateQuantity(d,d.getStock()-quantity);
               iMainPageView.setUser(iMainPageView.getConnection().getUser(iMainPageView.getUser().getUsername()));
               iMainPageView.setActualDiscountedAmount(MainPageView.actualDiscountedAmount+priceDiscounted*quantity);
            }
        }
    }

    public void addRating(DrugDto d) throws IOException {
        float currentRating=Float.parseFloat(iMainPageView.getRating());
        if(currentRating<1 || currentRating>5){
            iMainPageView.setInfo("Rating must be in 1-5 interval");
        }
        else {
            iMainPageView.getConnection().addRating(d.getName(),currentRating);
            iMainPageView.setInfo("Rating submited successfully");
        }
    }
}
