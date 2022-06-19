package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.Order;
import com.revature.models.Product;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.OrderRepository;
import com.revature.repositories.ProductRepository;
import com.revature.services.AuthorizationService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This is a controller that returns JSON
@RequestMapping("/users")   // This is the base URL for all of the methods in this controller
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AuthorizationService authorizationService;

    //Get logged in user
//@GetMapping("/me")



    @Authorized(allowedRoles = {Role.ADMIN})    // This method requires the user to be an admin
    @GetMapping // Map ONLY GET Requests
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Authorized(allowedRoles = {Role.ADMIN})
    @GetMapping("/{user_id}")
    public ResponseEntity<User> findById(@PathVariable("user_id") int user_id) {
        authorizationService.guardByUserId(user_id);
        return ResponseEntity.ok(userService.findById(user_id));
    }

    @PostMapping  //Registration
    public ResponseEntity<User> insert(@RequestBody User user) {
        return ResponseEntity.accepted().body(userService.insert(user));
    }

    @Authorized(allowedRoles = {Role.ADMIN, Role.EMPLOYEE})
    @PutMapping("/{user_id}")
    User updateUser(@PathVariable("user_id") int user_id, @RequestBody User user) {
        User userToUpdate = userService.findById(user_id);
        userToUpdate.setFirstname(user.getFirstname());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        return userService.update(userToUpdate);
    }


    @Authorized(allowedRoles = {Role.ADMIN})
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> delete(@PathVariable("user_id") int user_id) {
        if (userService.delete(user_id)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    }
