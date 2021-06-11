package utils;

import model.service.BoughtDrugService;
import model.service.DrugService;
import model.service.ShoppingCartService;
import model.service.UserService;

public class ServicesClassUtils {
    public static UserService userService=new UserService();
    public static DrugService drugService=new DrugService();
    public static BoughtDrugService boughtDrugService= new BoughtDrugService();
    public static ShoppingCartService shoppingCartService = new ShoppingCartService();
}
