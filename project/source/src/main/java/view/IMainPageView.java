package view;

import clientserver.Client;
import javafx.scene.control.TableView;
import model.dto.DrugDto;
import model.dto.ShoppingCartDto;
import model.entity.ShoppingCart;
import model.entity.User;

import java.util.Set;

public interface IMainPageView {
    String getQuantity();
    Client.Connection getConnection();

    User getUser();

    void setInfo(String msg);

    TableView<DrugDto> getDrugTable();

    TableView<ShoppingCartDto> getShoppingCartTable();

    Set<ShoppingCart> getShoppingCart();

    void setUser(User user);

    String getRating();

    void setActualDiscountedAmount(float v);
}
