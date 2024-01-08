package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String CustomerName;
    private String email;
    private String address;
    private String phoneNumber;
    @OneToMany (mappedBy = "customer")
    private List<Order> orders;

    public Customer(String customerName, String email, String address, String phoneNumber) {
        CustomerName = customerName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public void addOrder(Order order){
        order.setCustomer(this);
        orders.add(order);
    }
}
