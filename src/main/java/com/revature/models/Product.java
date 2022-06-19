package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "products") // This tells Hibernate to make a table out of this class
@Data   // This tells lombok to generate getters and setters for us
@AllArgsConstructor // This is a constructor that takes in all the fields
@NoArgsConstructor  // No-args constructor is required for Hibernate
public class Product {

    @Id // This tells Hibernate to make a primary key out of this field
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // This tells Hibernate to auto-increment this field
    @Column(nullable = false, unique = true, updatable = false)
    private int product_id;    // This tells Hibernate to create an int field for the id column in the database
    private String product_name;  // This tells Hibernate to create a varchar field for the name column in the database
    private double product_price; // This tells Hibernate to create a double field for the price column in the database
    private int product_quantity; // This tells Hibernate to create an int field for the quantity column in the database

    @JsonIgnore
    @ManyToMany(mappedBy = "products_order")
    private Set<Order> orders_product = new HashSet<>();

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public Set<Order> getOrders_product() {
        return orders_product;
    }

    public Set<Order> getOrders() {
        return null;
    }


}