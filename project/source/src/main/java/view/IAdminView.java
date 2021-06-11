package view;

import clientserver.Client;
import javafx.scene.control.TableView;

public interface IAdminView {
    String getUsername();
    String getPassword();
    String getNume();
    String getEmail();
    String getDrugName();
    String getType();
    String getPrice();
    String getQuantity();
    TableView getUserTable();
    TableView getDrugTable();
    Client.Connection getConnection();

    void setInfo(String message);

    String getDiscount();

    String getRating();

    String getQuantityDiscounted();
}
