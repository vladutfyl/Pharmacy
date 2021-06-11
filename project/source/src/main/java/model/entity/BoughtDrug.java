package model.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Entity
@Table( name = "BoughtDrug" )
public class BoughtDrug implements Serializable {
    @Id
    String id;
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

    public BoughtDrug() {
        // this form used by Hibernate
    }

    public BoughtDrug(String id, String name, String type, float price, int cantity, User user) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.cantity = cantity;
        this.user = user;
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

    @Override
    public String toString() {
        return "BoughtDrug{" +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + cantity +
                ", total="+ cantity*price+
                '}';
    }
}
