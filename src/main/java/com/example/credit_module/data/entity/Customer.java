package com.example.credit_module.data.entity;

import com.example.credit_module.data.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private BigDecimal creditLimit;
    private BigDecimal usedCreditLimit;

    /*private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    //Not persistent. There is no column on database table.
    @Transient
    private String token;*/
}
