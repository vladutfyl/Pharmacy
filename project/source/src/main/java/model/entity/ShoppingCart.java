package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Entity
@Table( name = "ShoppingCart" )
public class ShoppingCart implements Serializable {

    @Id
    private String id;
    @Column
    String name;
    @Column
    String type;
    @Column
    float price;
    @Column
    int cantity;
    @ManyToOne
    @JoinColumn(name = "idUser")
    User user;

    public ShoppingCart(String generateUUID, String nume, String type, float price, int quantity, User u) {
        this.id=generateUUID;
        this.name=nume;
        this.type=type;
        this.cantity=quantity;
        this.price=price;
        this.user=u;
    }

    public ShoppingCart(){
        //used by hibernate
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public int getCantity() {
        return cantity;
    }

    public void setCantity(int cantity) {
        this.cantity = cantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
