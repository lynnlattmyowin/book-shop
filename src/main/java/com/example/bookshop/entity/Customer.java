package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String customerName;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    @OneToMany (mappedBy = "customer")
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles
            = new HashSet<>();

    public Customer(String customerName, String email, String address, String phoneNumber) {
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public void addOrder(Order order){
        order.setCustomer(this);
        orders.add(order);
    }

    public void addRole(Role role){
        role.getCustomers().add(this);
        this.roles.add(role);
    }
}
