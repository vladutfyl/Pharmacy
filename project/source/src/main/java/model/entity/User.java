package model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "User" )
public class User implements Serializable {
    @Id
    private String idUser;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private float money;
    @OneToMany(mappedBy="user",fetch = FetchType.EAGER)
    private Set<BoughtDrug> boughtDrugs;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<ShoppingCart> shoppingCart;


    public User() {
        // this form used by Hibernate
    }

    public User(String id,String username,String password,String name,String email,float money) {
        // for application use, to create new events
        this.idUser=id;
        this.username = username;
        this.password=password;
        this.email=email;
        this.name=name;
        this.money=money;
    }

    public float getMoney() { return money; }

    public void setMoney(float money) { this.money = money; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<BoughtDrug> getBoughtDrugs() {
        return boughtDrugs;
    }

    public Set<ShoppingCart> getShoppingCart() { return shoppingCart; }

}
