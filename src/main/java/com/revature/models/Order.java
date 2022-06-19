package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "orders")  // This tells Hibernate to make a table out of this class
@Data // This tells lombok to generate getters and setters for us
@AllArgsConstructor    // This is a constructor that takes in all the fields
@NoArgsConstructor      // No-args constructor is required for Hibernate
public class Order {


    @Id // This tells Hibernate to make a primary key out of this field
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // This tells Hibernate to auto-increment this field
    @Column(nullable = false, unique = true, updatable = false)
    private int order_id;  // This tells Hibernate to create an int field for the id column in the database
    private int user_id;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "orders_product")
//    Set<Product> products_order;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products_order = new HashSet<>();

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }



    public void assignUser(User user) {
        user_id = user.getUser_id();
    }


    public void addUser(User user) {
        user_id = user.getUser_id();
    }

    public Set<Product> getProducts_order() {
        return products_order;
    }
    public void addProduct(Product product) {
        products_order.add(product);
    }


}
