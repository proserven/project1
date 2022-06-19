package com.revature.controllers;

import com.revature.advice.AuthAspect;
import com.revature.annotations.Authorized;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

//    @Autowired
//    UserService userService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    AuthAspect authAspect;


    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE, Role.CUSTOMER})
    @GetMapping()
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @GetMapping("/{order_id}")
    public ResponseEntity<Order> findById(@PathVariable("order_id") int order_id) {
        return ResponseEntity.ok(orderRepository.findById(order_id).get());
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.CUSTOMER, Role.EMPLOYEE})
    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order order) {
        return ResponseEntity.accepted().body(orderRepository.save(order));
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.CUSTOMER, Role.EMPLOYEE})
        @PostMapping("/{order_id}/{product_id}")
        //add product to order
        Order addProductToOrder( @PathVariable("order_id") int order_id,
                                 @PathVariable("product_id") int product_id){
            Order order = orderRepository.findById(order_id).get();
            Product product = productRepository.findById(product_id).get();
            order.addProduct(product);
            return orderRepository.save(order);
        }

    }