package model.service;

import model.entity.ShoppingCart;
import model.entity.User;
import model.repository.ShoppingCartRepo;
import utils.UuidGeneratorUtils;



public class ShoppingCartService {
    private ShoppingCartRepo shoppingCartRepo;

    public ShoppingCartService() {
        this.shoppingCartRepo = new ShoppingCartRepo();
    }

    public void add(String nume, String type, float price, int quantity, User u) {
        ShoppingCart boughtDrug = new ShoppingCart(UuidGeneratorUtils.generateUUID(), nume, type, price, quantity, u);
        boughtDrug.setUser(u);
        shoppingCartRepo.add(boughtDrug);
    }
}
