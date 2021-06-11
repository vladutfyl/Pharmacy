package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "Drug" )
public class Drug implements Serializable {
    @Id
    String id;
    @Column
    String name;
    @Column
    String type;
    @Column
    float price;
    @Column
    int stock;
    @Column
    float rating;
    @Column
    int nrReviews;
    @Column
    int discount;
    @Column
    int quantityDiscounted;

    public Drug() {
        // this form used by Hibernate
    }

    public Drug(String id, String name, String type, float price, int stock) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.stock = stock;
        this.discount=0;
        this.rating=0;
        this.nrReviews=0;
        this.quantityDiscounted=0;
    }

    public int getQuantityDiscounted() {
        return quantityDiscounted;
    }

    public void setQuantityDiscounted(int quantityDiscounted) {
        this.quantityDiscounted = quantityDiscounted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getNrReviews() {
        return nrReviews;
    }

    public void setNrReviews(int nrReviews) {
        this.nrReviews = nrReviews;
    }
}
