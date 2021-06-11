package model.dto;

import javafx.beans.property.SimpleStringProperty;

public class ShoppingCartDto {
    public SimpleStringProperty name;
    public SimpleStringProperty type;
    public SimpleStringProperty price;
    public SimpleStringProperty quantity;
    public SimpleStringProperty total;

    public ShoppingCartDto(String name, String type, float price, int quantity) {
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.price = new SimpleStringProperty(Float.toString(price));
        this.quantity = new SimpleStringProperty(Integer.toString(quantity));
        this.total = new SimpleStringProperty(Float.toString(price*quantity));
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
}
