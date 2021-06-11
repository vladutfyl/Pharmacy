package model.dto;

import javafx.beans.property.SimpleStringProperty;

public class DrugDto {

    public SimpleStringProperty name;
    public SimpleStringProperty type;
    public SimpleStringProperty price;
    public SimpleStringProperty stock;
    public SimpleStringProperty rating;
    public SimpleStringProperty discount;
    public SimpleStringProperty quantityDiscounted;

    public DrugDto(String name, String type, float price, int stock, float rating, int discount,int quantityDiscounted) {
        this.name=new SimpleStringProperty(name);
        this.type=new SimpleStringProperty(type);
        this.price=new SimpleStringProperty(Float.toString(price));
        this.stock=new SimpleStringProperty(Integer.toString(stock));
        this.rating=new SimpleStringProperty(Float.toString(rating));
        this.discount=new SimpleStringProperty(Integer.toString(discount));
        this.quantityDiscounted=new SimpleStringProperty(Integer.toString(quantityDiscounted));
    }

    public String getQuantityDiscounted() {
        return quantityDiscounted.get();
    }

    public SimpleStringProperty quantityDiscountedProperty() {
        return quantityDiscounted;
    }

    public void setQuantityDiscounted(String quantityDiscounted) {
        this.quantityDiscounted.set(quantityDiscounted);
    }

    public String getDiscount() {
        return discount.get();
    }

    public SimpleStringProperty discountProperty() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount.set(discount);
    }

    public String getRating() { return rating.get(); }

    public SimpleStringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
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

    public String getStock() {
        return stock.get();
    }

    public SimpleStringProperty stockProperty() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock.set(stock);
    }
}
